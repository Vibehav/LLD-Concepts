package org.concepts.service;

import org.concepts.enums.VehicleType;
import org.concepts.model.Vehicle;
import org.concepts.repository.VehicleRepository;

import java.util.Optional;

public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository){
        this.vehicleRepository=vehicleRepository;
    }

    public Vehicle getOrCreateVehicle(String vehicleNumber, String ownerName, VehicleType vehicleType){
        Optional<Vehicle> optionalVehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);
        Vehicle vehicle = null;
        if(optionalVehicle.isEmpty()){
            vehicle = Vehicle.builder()
                    .setOwnerName(ownerName)
                    .setVehicleType(vehicleType)
                    .setVehicleNumber(vehicleNumber).build();


            vehicle = vehicleRepository.save(vehicle);
        } else {
            vehicle = optionalVehicle.get();
        }
        return  vehicle;
    }

}
