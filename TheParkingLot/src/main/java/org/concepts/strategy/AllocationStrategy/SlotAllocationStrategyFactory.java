package org.concepts.strategy.AllocationStrategy;

import org.concepts.enums.OperatorPreference;
import org.concepts.enums.ParkingSlotType;
import org.concepts.model.ParkingLot;
import org.concepts.model.Vehicle;

public class SlotAllocationStrategyFactory {

    public static SlotAllocationStrategy getStrategy(OperatorPreference operatorPreference,
                                                     ParkingSlotType parkingSlotType){
        if(operatorPreference.equals(OperatorPreference.EV_CHARGING_REQUESTED)){
            return new EvSlotAllocationStrategy(true);
        }

        if(operatorPreference.equals(OperatorPreference.NONE) && parkingSlotType.equals(ParkingSlotType.VIP)) return new VipSlotAllocationStrategy();
        if(operatorPreference.equals(OperatorPreference.NONE) && parkingSlotType.equals(ParkingSlotType.STAFF)) return new StaffSlotAllocationStrategy();

        return new FarthestSlotAllocationStrategy();
    }
}
