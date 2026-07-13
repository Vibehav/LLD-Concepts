package org.concepts.dto;

import org.concepts.enums.OperatorPreference;
import org.concepts.enums.ParkingSlotType;
import org.concepts.enums.VehicleType;
import org.concepts.exception.InvalidRequestException;

import java.util.ArrayList;
import java.util.List;

public class IssueTokenRequest {

    private int gateId;
    private int parkingLotId;
    private String ownerName;
    private String vehicleNumber;
    private VehicleType vehicleType;

    //captures manual button presses from the gate operator terminal
    private OperatorPreference operatorPreference;
    private ParkingSlotType parkingSlotType;

    private IssueTokenRequest(Builder b){
        this.gateId = b.gateId;
        this.parkingLotId = b.parkingLotId;
        this.ownerName = b.ownerName;
        this.vehicleNumber = b.vehicleNumber;
        this.vehicleType = b.vehicleType;
        this.operatorPreference = b.operatorPreference;
        this.parkingSlotType = b.parkingSlotType;
    }

    public ParkingSlotType getParkingSlotType() {return parkingSlotType;}
    public void setParkingSlotType(ParkingSlotType parkingSlotType) {
        this.parkingSlotType = parkingSlotType;
    }
    public OperatorPreference getOperatorPreference() {
        return operatorPreference;
    }
    public void setOperatorPreference(OperatorPreference operatorPreference) {this.operatorPreference = operatorPreference;}
    public int getParkingLotId() {
        return parkingLotId;
    }
    public void setParkingLotId(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }
    public int getGateId() {
        return gateId;
    }
    public void setGateId(int gateId) {
        this.gateId = gateId;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    public VehicleType getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    // Builder

    public static Builder builder() { return new Builder(); }

    public static class Builder{
        private int gateId;
        private int parkingLotId;
        private String ownerName;
        private String vehicleNumber;
        private VehicleType vehicleType;
        private OperatorPreference operatorPreference;
        private ParkingSlotType parkingSlotType;

        public Builder gateId(int gateId) { this.gateId = gateId; return this; }
        public Builder parkingLotId(int parkingLotId) { this.parkingLotId = parkingLotId; return this; }
        public Builder ownerName(String ownerName) { this.ownerName = ownerName; return this; }
        public Builder vehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; return this; }
        public Builder vehicleType(VehicleType vehicleType) { this.vehicleType = vehicleType; return this; }
        public Builder operatorPreference(OperatorPreference p) { this.operatorPreference = p; return this; }
        public Builder parkingSlotType(ParkingSlotType t) { this.parkingSlotType = t; return this; }

        public IssueTokenRequest build() {
            IssueTokenRequest request = new IssueTokenRequest(this);
            RequestValidator.validate(request); // <-- validation hook, see below
            return request;
        }
    }
}

