package org.concepts.repository;

import org.concepts.model.Vehicle;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VehicleRepository {

    private final Map<String, Vehicle> vehicleMap;
    private int vehicleId=0;

    public VehicleRepository(Map<String,Vehicle> vehicleMap){
        this.vehicleMap = vehicleMap;
    }

    public Optional<Vehicle> findByVehicleNumber(String vehicleNumber){
        if(vehicleMap.containsKey(vehicleNumber)){
            return Optional.of(vehicleMap.get(vehicleNumber));
        }
        return Optional.empty();
    }

    public Vehicle save(Vehicle vehicle) {

        if(vehicle.getVehicleNumber()!=null){
            vehicle.setUpdatedAt(new Date());
            vehicleMap.put(vehicle.getVehicleNumber(), vehicle);
            return vehicleMap.get(vehicle.getVehicleNumber());
        }
        this.vehicleId+=1;
        vehicle.setId(vehicleId);
        vehicle.setCreatedAt(new Date());
        vehicle.setUpdatedAt(new Date());
        vehicleMap.put(vehicle.getVehicleNumber(), vehicle);
        return vehicle;
    }

}
