package org.concepts.strategy.AllocationStrategy;

import org.concepts.enums.OperatorPreference;
import org.concepts.enums.ParkingSlotType;
import org.concepts.model.*;
import org.concepts.enums.ParkingSlotStatus;
import org.concepts.util.SlotAllocationResult;

public class FarthestSlotAllocationStrategy implements SlotAllocationStrategy {
    @Override
    public SlotAllocationResult assignSlot(ParkingLot parkingLot, Vehicle vehicle) {
        for (int i = parkingLot.getFloors().size() - 1; i >= 0; i--) {
            ParkingFloor floor = parkingLot.getFloors().get(i);
            for (int j = floor.getSlots().size() - 1; j >= 0; j--) {
                ParkingSlot slot = floor.getSlots().get(j);

                if (slot.getParkingSlotStatus() == ParkingSlotStatus.EMPTY &&
                        slot.getAllowedType() == vehicle.getVehicleType() &&
                        !slot.isHasEvCharger() && slot.getParkingSlotType().equals(ParkingSlotType.GENERAL)) {          // <-- reserve EV slots
                    return new SlotAllocationResult(slot, true);
                }
            }
        }
        return new SlotAllocationResult(null, false);
    }
}