package org.concepts.model;

import org.concepts.enums.ParkingSlotStatus;
import org.concepts.enums.ParkingSlotType;
import org.concepts.enums.VehicleType;

public class ParkingSlot extends BaseEntity{


    private String slotNumber;
    private VehicleType allowedType;
    private Vehicle vehicle;
    private ParkingSlotStatus parkingSlotStatus;
    private ParkingSlotType parkingSlotType;
    private boolean hasEvCharger;

    public ParkingSlot(String slotNumber, VehicleType allowedType, Vehicle vehicle,ParkingSlotType parkingSlotType, ParkingSlotStatus parkingSlotStatus) {
        this.slotNumber = slotNumber;
        this.allowedType = allowedType;
        this.vehicle = vehicle;
        this.parkingSlotStatus = parkingSlotStatus;
        this.parkingSlotType=parkingSlotType;
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

    //    public ParkingFloor getParkingFloor() {
//        return parkingFloor;
//    }
//
//    public void setParkingFloor(ParkingFloor parkingFloor) {
//        this.parkingFloor = parkingFloor;
//    }
}

/**
 * Schema Design
 * parking_slot
 * id - primitve
 * slot_number - primitve
 *
 */
