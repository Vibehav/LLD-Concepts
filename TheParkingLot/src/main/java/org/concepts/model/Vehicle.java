package org.concepts.model;

import org.concepts.enums.VehicleType;
import org.concepts.repository.VehicleRepository;

public class Vehicle extends BaseEntity {

    private VehicleType vehicleType;
    private String vehicleNumber;
    private String OwnerName;

    public VehicleType getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    public String getOwnerName() {
        return OwnerName;
    }
    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }
    private Vehicle(){}
    private Vehicle(Builder b){
        this.setOwnerName(b.OwnerName);
        this.setVehicleType(b.vehicleType);
        this.setVehicleNumber(b.vehicleNumber);
    }
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private VehicleType vehicleType;
        private String vehicleNumber;
        private String OwnerName;

        public Builder setVehicleType(VehicleType vehicleType) {
            this.vehicleType = vehicleType;
            return this;
        }
        public Builder setVehicleNumber(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
            return this;
        }
        public Builder setOwnerName(String ownerName) {
            this.OwnerName = ownerName;
            return this;
        }

        public Vehicle build(){
            // validate vehicle
            return new Vehicle(this);
        }
    }

}
/**
 * Schema Design
 * vehicle
 * id - primitive
 * vehicle_number - primitive
 * owner_name - primitive
 *
 */
