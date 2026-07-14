package org.concepts.service;

import org.concepts.dto.IssueTokenRequest;
import org.concepts.enums.ParkingSlotStatus;
import org.concepts.exception.GateNotFoundException;
import org.concepts.exception.ParkingLotNotFoundException;
import org.concepts.exception.ParkingSlotNotFoundException;
import org.concepts.model.*;
import org.concepts.repository.*;
import org.concepts.strategy.AllocationStrategy.SlotAllocationStrategy;
import org.concepts.strategy.AllocationStrategy.SlotAllocationStrategyFactory;
import org.concepts.util.SlotAllocationResult;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class TokenService {

    private final ParkingLotRepository parkingLotRepository;
    private final GateRepository gateRepository;
    private final ParkingSlotRepository parkingSlotRepository;
    private final TokenRepository tokenRepository;
    private final VehicleService vehicleService;

    public TokenService(ParkingLotRepository parkingLotRepository,
                        GateRepository gateRepository,
                        ParkingSlotRepository parkingSlotRepository,
                        TokenRepository tokenRepository,
                        VehicleService vehicleService) {
        this.parkingLotRepository=parkingLotRepository;
        this.gateRepository=gateRepository;
        this.parkingSlotRepository=parkingSlotRepository;
        this.vehicleService = vehicleService;
        this.tokenRepository=tokenRepository;
    }



    public Token issueToken(IssueTokenRequest issueTokenRequest) {
       // Fetch Parking Lot and Gate
        Optional<ParkingLot> optionalParkingLot = parkingLotRepository.findById(issueTokenRequest.getParkingLotId());
        if(optionalParkingLot.isEmpty()){
            throw new ParkingLotNotFoundException("Parking Lot not found with Id:"+ issueTokenRequest.getParkingLotId());
        }

        Optional<Gate> optionalGate = gateRepository.findById(issueTokenRequest.getGateId());
        if(optionalGate.isEmpty()){
            throw new GateNotFoundException("Gate not found with the associated Id:" + issueTokenRequest.getGateId());
        }

        ParkingLot parkingLot = optionalParkingLot.get();
        Gate gate = optionalGate.get();

        // Check if the vehicle is present in the DB
        Vehicle currVehicle = vehicleService.getOrCreateVehicle(issueTokenRequest.getVehicleNumber(),
                                                                issueTokenRequest.getOwnerName(),
                                                                issueTokenRequest.getVehicleType());

        // Step 3: Use Factory to dynamically resolve the required strategy
        // Based on vehicle records and operator buttons (EV)
        SlotAllocationStrategy strategy = SlotAllocationStrategyFactory.getStrategy(issueTokenRequest.getOperatorPreference(),
                                                                                    issueTokenRequest.getParkingSlotType());
        // Step 4: Execute the resolved allocation strategy
        SlotAllocationResult wrapper = strategy.assignSlot(parkingLot,currVehicle); // wrapper to identify which strategy was used
        ParkingSlot assignedSlot = wrapper.getSlot();
        if(assignedSlot==null) {
            throw new ParkingSlotNotFoundException("Parking Slot is Full");
        }
        assignedSlot.setVehicle(currVehicle);
        assignedSlot.setParkingSlotStatus(ParkingSlotStatus.FILLED);
        assignedSlot = parkingSlotRepository.save(assignedSlot);

        // Generate the token
        Token token = Token.builder()
                            .setTokenNumber(UUID.randomUUID().toString())
                            .setEntryTime(new Date())
                            .setAssignedSlot(assignedSlot)
                            .setEntryGate(gate)
                            .setVehicle(currVehicle).build();

        token = tokenRepository.save(token);


        return token;
    }
}
