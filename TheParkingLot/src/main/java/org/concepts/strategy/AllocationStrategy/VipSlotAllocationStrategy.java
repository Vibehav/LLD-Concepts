package org.concepts.strategy.AllocationStrategy;

import org.concepts.enums.ParkingSlotStatus;
import org.concepts.enums.ParkingSlotType;
import org.concepts.model.ParkingFloor;
import org.concepts.model.ParkingLot;
import org.concepts.model.ParkingSlot;
import org.concepts.model.Vehicle;
import org.concepts.util.SlotAllocationResult;

public class VipSlotAllocationStrategy implements SlotAllocationStrategy {
    @Override
    public SlotAllocationResult assignSlot(ParkingLot parkingLot, Vehicle vehicle) {
        for (ParkingFloor parkingFloor:parkingLot.getFloors()){
            for (ParkingSlot parkingSlot:parkingFloor.getSlots()){
                if(parkingSlot.getParkingSlotType().equals(ParkingSlotType.VIP)
                        && parkingSlot.getParkingSlotStatus().equals(ParkingSlotStatus.EMPTY)
                        && parkingSlot.getAllowedType().equals(vehicle.getVehicleType())){
                    return new SlotAllocationResult(parkingSlot,true);
                }
            }
        }

        SlotAllocationResult fallbackResult = new FarthestSlotAllocationStrategy().assignSlot(parkingLot, vehicle);
        return new SlotAllocationResult(fallbackResult.getSlot(), false);
    }
}
