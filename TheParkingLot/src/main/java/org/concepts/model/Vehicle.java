package org.concepts.model;

import org.concepts.enums.VehicleType;

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
}
/**
 * Schema Design
 * vehicle
 * id - primitive
 * vehicle_number - primitive
 * owner_name - primitive
 *
 */
