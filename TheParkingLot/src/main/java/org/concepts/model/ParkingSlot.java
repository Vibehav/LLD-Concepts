package org.concepts.model;

import org.concepts.enums.ParkingSlotStatus;
import org.concepts.enums.ParkingSlotType;
import org.concepts.enums.VehicleType;

public class ParkingSlot extends BaseEntity{


    private String slotNumber;
    private ParkingSlotStatus parkingSlotStatus;
    private VehicleType allowedType;
    private ParkingSlotType parkingSlotType;
    private Vehicle vehicle;
    private boolean hasEvCharger;

    private ParkingSlot(Builder b) {
        this.slotNumber = b.slotNumber;
        this.allowedType = b.allowedType;
        this.vehicle = b.vehicle;
        this.parkingSlotStatus = b.parkingSlotStatus;
        this.parkingSlotType= b.parkingSlotType;
        this.hasEvCharger=b.hasEvCharger;
    }
    private ParkingSlot(){}

    public static Builder builder(){
        return new Builder();
    }

//bidirectional
//    private ParkingFloor parkingFloor;


    public String getSlotNumber() {
        return slotNumber;
    }
    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }
    public VehicleType getAllowedType() {
        return allowedType;
    }
    public void setAllowedType(VehicleType allowedType) {
        this.allowedType = allowedType;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public ParkingSlotStatus getParkingSlotStatus() {
        return parkingSlotStatus;
    }
    public void setParkingSlotStatus(ParkingSlotStatus parkingSlotStatus) {
        this.parkingSlotStatus = parkingSlotStatus;
    }
    public ParkingSlotType getParkingSlotType() {
        return parkingSlotType;
    }
    public void setParkingSlotType(ParkingSlotType parkingSlotType) {
        this.parkingSlotType = parkingSlotType;
    }
    public boolean isHasEvCharger() {
        return hasEvCharger;
    }
    public void setHasEvCharger(boolean hasEvCharger) {
        this.hasEvCharger = hasEvCharger;
    }

    public static class Builder{
        private String slotNumber;
        private VehicleType allowedType;
        private Vehicle vehicle;
        private ParkingSlotStatus parkingSlotStatus;
        private ParkingSlotType parkingSlotType;
        private boolean hasEvCharger;

        public Builder setSlotNumber(String slotNumber) {
            this.slotNumber = slotNumber;
            return this;
        }
        public Builder setAllowedType(VehicleType allowedType) {
            this.allowedType = allowedType;
            return this;
        }
        public Builder setVehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
            return this;
        }
        public Builder setParkingSlotStatus(ParkingSlotStatus parkingSlotStatus) {
            this.parkingSlotStatus = parkingSlotStatus;
            return this;
        }
        public Builder setParkingSlotType(ParkingSlotType parkingSlotType) {
            this.parkingSlotType = parkingSlotType;
            return this;
        }
        public Builder setHasEvCharger(boolean hasEvCharger) {
            this.hasEvCharger = hasEvCharger;
            return this;
        }

        public ParkingSlot build(){
            //validations
            return new ParkingSlot(this);
        }
    }
}

    //    public ParkingFloor getParkingFloor() {
//        return parkingFloor;
//    }
//
//    public void setParkingFloor(ParkingFloor parkingFloor) {
//        this.parkingFloor = parkingFloor;
//    }

/**
 * Schema Design
 * parking_slot
 * id - primitve
 * slot_number - primitve
 *
 */
