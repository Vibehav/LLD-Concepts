package org.concepts.repository;

import org.concepts.model.Gate;
import org.concepts.model.ParkingLot;
import org.concepts.model.ParkingSlot;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ParkingLotRepository {

    private final Map<Integer, ParkingLot> parkingLotMap;
    private int parkingLotId = 0;

    public ParkingLotRepository(Map<Integer, ParkingLot> parkingLotMap){
        this.parkingLotMap= parkingLotMap;
    }

    public Optional<ParkingLot> findById(int parkingLotId){
       ParkingLot parkingLot = parkingLotMap.get(parkingLotId);
       if(parkingLot == null) {
           return Optional.empty();
       }
       return Optional.of(parkingLot);
    }

    public ParkingLot save(ParkingLot parkingLot){

        if(parkingLot.getId()!=0){
            parkingLot.setUpdatedAt(new Date());
            parkingLotMap.put(parkingLot.getId(),parkingLot);
            return parkingLot;
        }

        this.parkingLotId+=1;
        parkingLot.setId(parkingLotId);
        parkingLot.setCreatedAt(new Date());
        parkingLot.setUpdatedAt(new Date());
        parkingLotMap.put(parkingLot.getId(),parkingLot);
        return parkingLot;


    }

}
