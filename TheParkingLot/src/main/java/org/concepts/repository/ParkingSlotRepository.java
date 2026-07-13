package org.concepts.repository;


import org.concepts.model.ParkingSlot;

import java.util.Date;
import java.util.Map;

public class ParkingSlotRepository {

    private Map<Integer, ParkingSlot> parkingSlotDb;
    private int genId = 0;

    public ParkingSlotRepository(Map<Integer,ParkingSlot> parkingSlotDb){
        this.parkingSlotDb = parkingSlotDb;
    }

    public ParkingSlot save(ParkingSlot parkingSlot){
        if(parkingSlot.getId()!=0){
            parkingSlot.setUpdatedAt(new Date());
            parkingSlotDb.put(parkingSlot.getId(), parkingSlot);
            return parkingSlot;
        }
        this.genId += 1;
        parkingSlot.setId(genId);
        parkingSlot.setCreatedAt(new Date());
        parkingSlot.setUpdatedAt(new Date());

        parkingSlotDb.put(genId, parkingSlot);
        return parkingSlot;
    }
}
