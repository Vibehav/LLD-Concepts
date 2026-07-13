package org.concepts.model;

import java.util.List;

public class ParkingFloor extends BaseEntity {


    private List<ParkingSlot> slots;
    private String floorNumber;

    public List<ParkingSlot> getSlots() {
        return slots;
    }

    public void setSlots(List<ParkingSlot> slots) {
        this.slots = slots;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }
}

/**
 * Schema Design
 * parking_floor
 * id - primitve
 * floor_number - primitve
 *
 */
