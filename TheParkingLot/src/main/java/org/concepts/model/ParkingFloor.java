package org.concepts.model;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor extends BaseEntity {

    private List<ParkingSlot> slots;
    private String floorNumber;

    private ParkingFloor() {
    }

    private ParkingFloor(Builder builder) {
        this.slots = builder.slots;
        this.floorNumber = builder.floorNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<ParkingSlot> slots = new ArrayList<>();
        private String floorNumber;

        public Builder slots(List<ParkingSlot> slots) {
            this.slots = slots;
            return this;
        }

        public Builder addSlot(ParkingSlot parkingSlot) {
            this.slots.add(parkingSlot);
            return this;
        }

        public Builder floorNumber(String floorNumber) {
            this.floorNumber = floorNumber;
            return this;
        }

        public ParkingFloor build() {

            if (floorNumber == null || floorNumber.isBlank()) {
                throw new IllegalStateException("Floor number is mandatory.");
            }

            return new ParkingFloor(this);
        }
    }

    public void addSlot(ParkingSlot parkingSlot) {
        slots.add(parkingSlot);
    }

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