package org.concepts.strategy.AllocationStrategy;

import org.concepts.enums.ParkingSlotStatus;
import org.concepts.enums.ParkingSlotType;
import org.concepts.enums.VehicleType;
import org.concepts.model.ParkingFloor;
import org.concepts.model.ParkingLot;
import org.concepts.model.ParkingSlot;
import org.concepts.model.Vehicle;
import org.concepts.util.SlotAllocationResult;

public class EvSlotAllocationStrategy implements SlotAllocationStrategy{

    private final boolean isEvChargingExplicitlyRequested;

    public EvSlotAllocationStrategy(boolean isEvChargingExplicitlyRequested){
        this.isEvChargingExplicitlyRequested=isEvChargingExplicitlyRequested;
    }

    @Override
    public SlotAllocationResult assignSlot(ParkingLot parkingLot, Vehicle vehicle) {

        // if true find ev chargers slots and assign them
        if(isEvChargingExplicitlyRequested){
            for (ParkingFloor parkingFloor: parkingLot.getFloors()){
                for (ParkingSlot parkingSlot: parkingFloor.getSlots()){

                    if(parkingSlot.getParkingSlotStatus().equals(ParkingSlotStatus.EMPTY)
                    && parkingSlot.getAllowedType().equals(vehicle.getVehicleType())
                    && parkingSlot.isHasEvCharger()) {
                        return new SlotAllocationResult(parkingSlot,true);
                    }
                }
            }
        }

        SlotAllocationResult fallbackResult = new FarthestSlotAllocationStrategy().assignSlot(parkingLot, vehicle);
        return new SlotAllocationResult(fallbackResult.getSlot(), false);

    }
}
