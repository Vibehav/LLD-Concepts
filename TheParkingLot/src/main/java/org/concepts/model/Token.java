package org.concepts.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Token extends BaseEntity{

    private String tokenNumber;
    private Date entryTime;
    private Vehicle vehicle;
    private Gate entryGate;
    private ParkingSlot assignedSlot;
    private Token(){}

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

    private Token(Builder b){
        this.tokenNumber=b.tokenNumber;
        this.entryTime=b.entryTime;
        this.vehicle=b.vehicle;
        this.entryGate=b.entryGate;
        this.assignedSlot=b.assignedSlot;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String tokenNumber;
        private Date entryTime;
        private Vehicle vehicle;
        private Gate entryGate;
        private ParkingSlot assignedSlot;

        public Builder setTokenNumber(String tokenNumber) {
            this.tokenNumber = tokenNumber;
            return this;
        }
        public Builder setEntryTime(Date entryTime) {
            this.entryTime = entryTime;
            return this;
        }
        public Builder setVehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
            return this;
        }
        public Builder setEntryGate(Gate entryGate) {
            this.entryGate = entryGate;
            return this;
        }
        public Builder setAssignedSlot(ParkingSlot assignedSlot) {
            this.assignedSlot = assignedSlot;
        return this;
        }

        public Token build(){
            //validate the token inputs
            return new Token(this);
        }
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
