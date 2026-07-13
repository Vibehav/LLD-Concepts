package org.concepts.strategy.AllocationStrategy;

import org.concepts.model.ParkingLot;
import org.concepts.model.Vehicle;
import org.concepts.util.SlotAllocationResult;

public interface SlotAllocationStrategy {

    SlotAllocationResult assignSlot(ParkingLot parkingLot, Vehicle vehicle);
}
