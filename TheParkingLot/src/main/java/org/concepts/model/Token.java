package org.concepts.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Token extends BaseEntity{

    private String tokenNumber;
    private Date entryTime;
    private Vehicle vehicle;
    private Gate entryGate;
    private ParkingSlot assignedSlot;


    public String getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Gate getEntryGate() {
        return entryGate;
    }

    public void setEntryGate(Gate entryGate) {
        this.entryGate = entryGate;
    }

    public ParkingSlot getAssignedSlot() {
        return assignedSlot;
    }

    public void setAssignedSlot(ParkingSlot assignedSlot) {
        this.assignedSlot = assignedSlot;
    }

}

/**
 * Schema Design
 * token
 * id - primitive
 * token_number - primitive
 * entry_time - TimeStamp
 *
 */
