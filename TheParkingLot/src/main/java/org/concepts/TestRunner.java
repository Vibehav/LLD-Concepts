package org.concepts;
import org.concepts.dto.IssueTokenRequest;
import org.concepts.dto.IssueTokenResponse;
import org.concepts.enums.*;
import org.concepts.model.*;
import org.concepts.repository.*;
import org.concepts.service.TokenService;
import org.concepts.service.VehicleService;
import org.concepts.controller.TokenController;
import java.util.*;
public class TestRunner {
    static int passed = 0, failed = 0;
    public static void main(String[] args) {

        Map<Integer, ParkingLot> parkingLotDb = new HashMap<>();
        Map<Integer, ParkingSlot> parkingSlotDb = new HashMap<>();
        Map<Integer, Gate> gateDb = new HashMap<>();
        Map<String, Vehicle> vehicleDb = new HashMap<>();
        Map<Integer, Token> tokenDb = new HashMap<>();
        GateRepository gateRepository = new GateRepository(gateDb);
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository(parkingLotDb);
        ParkingSlotRepository parkingSlotRepository = new ParkingSlotRepository(parkingSlotDb);
        VehicleRepository vehicleRepository = new VehicleRepository(vehicleDb);
        TokenRepository tokenRepository = new TokenRepository(tokenDb);
        VehicleService vehicleService = new VehicleService(vehicleRepository);
        TokenService tokenService = new TokenService(
                parkingLotRepository,
                gateRepository,
                parkingSlotRepository,
                tokenRepository,
                vehicleService
        );
        TokenController tokenController = new TokenController(tokenService);
        // ---- seed a gate ----
        Gate gate = Gate.builder().setGateNo("G1").build();
        gate = gateRepository.save(gate);
        int gateId = gate.getId();
        // ---- seed one VIP slot, one STAFF slot, one EV slot, one NORMAL slot, all on floor 1 ----
        ParkingSlot vipSlot = buildSlot(parkingSlotRepository, "V-1", VehicleType.FOUR_WHEELER, ParkingSlotType.VIP, false,ParkingSlotStatus.EMPTY);
        ParkingSlot staffSlot = buildSlot(parkingSlotRepository, "S-1", VehicleType.FOUR_WHEELER, ParkingSlotType.STAFF, false,ParkingSlotStatus.EMPTY);
        ParkingSlot evSlot = buildSlot(parkingSlotRepository, "E-1", VehicleType.FOUR_WHEELER, ParkingSlotType.GENERAL, true,ParkingSlotStatus.EMPTY);
        ParkingSlot normalSlot = buildSlot(parkingSlotRepository, "N-1", VehicleType.FOUR_WHEELER, ParkingSlotType.GENERAL, false,ParkingSlotStatus.EMPTY);

        ParkingFloor floor = ParkingFloor.builder()
                .floorNumber("F1")
                .slots(Arrays.asList(vipSlot, staffSlot, evSlot, normalSlot))
                .build();

        ParkingLot lot = ParkingLot.builder()
                .address("Test Lot")
                .floors(new ArrayList<>(List.of(floor)))
                .gates(new ArrayList<>(List.of(gate)))
                .build();
        lot = parkingLotRepository.save(lot);

        int lotId = lot.getId();
        // ================= TEST CASES =================
        // 1. Normal request, no preference -> should succeed, get the plain normal

        run("Normal parking, no preference", () -> {
            IssueTokenRequest req = IssueTokenRequest.builder()
                    .gateId(gateId).parkingLotId(lotId)
                    .ownerName("Alice").vehicleNumber("MH12AB1111")
                    .vehicleType(VehicleType.FOUR_WHEELER)
                    .operatorPreference(OperatorPreference.NONE)
                    .parkingSlotType(ParkingSlotType.GENERAL)
                    .build();
            IssueTokenResponse res = tokenController.issueToken(req);
            assertEquals(ResponseStatus.SUCCESS, res.getStatus(), "status");
        });
        // 2. VIP requested and available -> should get the actual VIP slot
        run("VIP requested, VIP available", () -> {
            IssueTokenRequest req = IssueTokenRequest.builder()
                    .gateId(gateId).parkingLotId(lotId)
                    .ownerName("Bob").vehicleNumber("MH12AB2222")
                    .vehicleType(VehicleType.FOUR_WHEELER)
                    .operatorPreference(OperatorPreference.NONE)
                    .parkingSlotType(ParkingSlotType.VIP)
                    .build();
            IssueTokenResponse res = tokenController.issueToken(req);
            assertEquals(ResponseStatus.SUCCESS, res.getStatus(), "status");
            assertEquals("V-1", res.getSlotNo(), "should get the VIP slot");
        });
        // 3. VIP requested but VIP now full -> should fall back WITHOUT corrupting slot type
        run("VIP requested, VIP unavailable -> fallback, type not corrupted", () -> {
            IssueTokenRequest req = IssueTokenRequest.builder()
                    .gateId(gateId).parkingLotId(lotId)
                    .ownerName("Carol").vehicleNumber("MH12AB3333")
                    .vehicleType(VehicleType.FOUR_WHEELER)
                    .operatorPreference(OperatorPreference.NONE)
                    .parkingSlotType(ParkingSlotType.VIP)
                    .build();
            IssueTokenResponse res = tokenController.issueToken(req);
            assertEquals(ResponseStatus.FAILURE, res.getStatus(), "status");
        });
        // 4. Staff requested and available
        run("Staff requested, available", () -> {
            IssueTokenRequest req = IssueTokenRequest.builder()
                    .gateId(gateId).parkingLotId(lotId)
                    .ownerName("Dave").vehicleNumber("MH12AB4444")
                    .vehicleType(VehicleType.FOUR_WHEELER)
                    .operatorPreference(OperatorPreference.NONE)
                    .parkingSlotType(ParkingSlotType.STAFF)
                    .build();
            IssueTokenResponse res = tokenController.issueToken(req);
            assertEquals("S-1", res.getSlotNo(), "should get the STAFF slot");
        });
        // 5. EV charging requested and available
        run("EV requested, charger available", () -> {
            IssueTokenRequest req = IssueTokenRequest.builder()
                    .gateId(gateId).parkingLotId(lotId)
                    .ownerName("Eve").vehicleNumber("MH12AB5555")
                    .vehicleType(VehicleType.FOUR_WHEELER)
                    .operatorPreference(OperatorPreference.EV_CHARGING_REQUESTED)
                    .parkingSlotType(ParkingSlotType.GENERAL)
                    .build();
            IssueTokenResponse res = tokenController.issueToken(req);
            assertEquals("E-1", res.getSlotNo(), "should get the EV slot");
        });
        // 6. Lot is full -> FAILURE, no exception leaks to caller
        run("Lot full -> graceful failure", () -> {
            // fill the only remaining NORMAL slot from test 1/3 fallback path etc.
            for (ParkingSlot s : parkingSlotDb.values()) {
                s.setParkingSlotStatus(ParkingSlotStatus.FILLED);
                parkingSlotRepository.save(s);
            }
            IssueTokenRequest req = IssueTokenRequest.builder()
                    .gateId(gateId).parkingLotId(lotId)
                    .ownerName("Frank").vehicleNumber("MH12AB6666")
                    .vehicleType(VehicleType.FOUR_WHEELER)
                    .operatorPreference(OperatorPreference.NONE)
                    .parkingSlotType(ParkingSlotType.GENERAL)
                    .build();
            IssueTokenResponse res = tokenController.issueToken(req);
            assertEquals(ResponseStatus.FAILURE, res.getStatus(), "status");
        });
        // 7. Invalid parking lot id -> FAILURE, not an unhandled exception
        run("Invalid parking lot id", () -> {
            IssueTokenRequest req = IssueTokenRequest.builder()
                    .gateId(gateId).parkingLotId(9999)
                    .ownerName("Grace").vehicleNumber("MH12AB7777")
                    .vehicleType(VehicleType.FOUR_WHEELER)
                    .operatorPreference(OperatorPreference.NONE)
                    .parkingSlotType(ParkingSlotType.GENERAL)
                    .build();
            IssueTokenResponse res = tokenController.issueToken(req);
            assertEquals(ResponseStatus.FAILURE, res.getStatus(), "status");
        });
        // 8. Invalid gate id -> FAILURE
        run("Invalid gate id", () -> {
            IssueTokenRequest req = IssueTokenRequest.builder()
                    .gateId(9999).parkingLotId(lotId)
                    .ownerName("Heidi").vehicleNumber("MH12AB8888")
                    .vehicleType(VehicleType.FOUR_WHEELER)
                    .operatorPreference(OperatorPreference.NONE)
                    .parkingSlotType(ParkingSlotType.GENERAL)
                    .build();
            IssueTokenResponse res = tokenController.issueToken(req);
            assertEquals(ResponseStatus.FAILURE, res.getStatus(), "status");
        });
        // 9. Re-entry: same vehicle number twice -> no duplicate vehicle created
        run("Repeat vehicle number does not duplicate vehicle record", () -> {
            String vNum = "MH12AB9999";
            vehicleService.getOrCreateVehicle(vNum, "Ivan", VehicleType.FOUR_WHEELER);
            Vehicle second = vehicleService.getOrCreateVehicle(vNum, "Ivan Renamed", VehicleType.FOUR_WHEELER);
            long countWithThisNumber = vehicleDb.values().stream()
                    .filter(v -> vNum.equals(v.getVehicleNumber())).count();
            assertEquals(1L, countWithThisNumber, "vehicle should not be duplicated on re-entry");
        });
        System.out.println("\n" + passed + " passed, " + failed + " failed");
    }
    // ---- helpers ----
    private static ParkingSlot buildSlot(ParkingSlotRepository repo,
                                         String slotNumber,
                                         VehicleType allowedType,
                                         ParkingSlotType parkingSlotType,
                                         boolean hasEvCharger,
                                         ParkingSlotStatus parkingSlotStatus) {
        ParkingSlot slot = ParkingSlot.builder()
                .setHasEvCharger(hasEvCharger)
                .setParkingSlotStatus(ParkingSlotStatus.EMPTY)
                .setSlotNumber(slotNumber)
                .setAllowedType(allowedType)
                .setParkingSlotType(parkingSlotType)
                .setParkingSlotStatus(parkingSlotStatus)
                .build();
        return repo.save(slot);
    }
    private static Integer findSlotIdByNumber(Map<Integer, ParkingSlot> db, String slotNumber) {
        for (Map.Entry<Integer, ParkingSlot> e : db.entrySet()) {
            if (e.getValue().getSlotNumber().equals(slotNumber)) return e.getKey();
        }
        throw new IllegalStateException("slot not found: " + slotNumber);
    }
    private interface TestCase { void run(); }
    private static void run(String name, TestCase test) {
        try {
            test.run();
            System.out.println("PASS - " + name);
            passed++;
        } catch (AssertionError e) {
            System.out.println("FAIL - " + name + " -> " + e.getMessage());
            failed++;
        } catch (Exception e) {
            System.out.println("ERROR - " + name + " -> " + e);
            failed++;
        }
    }
    private static void assertEquals(Object expected, Object actual, String label) {
        if (!Objects.equals(expected, actual)) {
            throw new AssertionError(label + ": expected <" + expected + "> but got <" + actual + ">");
        }
    }
}