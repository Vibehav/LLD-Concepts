package org.concepts.util;

import org.concepts.model.ParkingSlot;

public class SlotAllocationResult {
    private final ParkingSlot slot;
    private final boolean preferenceMethod;

    public SlotAllocationResult(ParkingSlot slot, boolean preferenceMethod){
        this.preferenceMethod=preferenceMethod;
        this.slot=slot;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public boolean isPreferenceMethod() {
        return preferenceMethod;
    }

}
