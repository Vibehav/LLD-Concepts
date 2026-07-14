package org.concepts.repository;

import org.concepts.model.Gate;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

public class GateRepository {
    private Map<Integer, Gate> gateMap;
    private int gateId =0;

    public GateRepository(Map<Integer,Gate> gateMap){
        this.gateMap=gateMap;
    }

    public Optional<Gate> findById(int gateId){
        if(gateMap.containsKey(gateId)){
            return Optional.of(gateMap.get(gateId));
        }
        return Optional.empty();
    }

    public Gate save(Gate gate){
        if(gate.getId()!=0){
            gate.setUpdatedAt(new Date());
             gateMap.put(gate.getId(),gate);
             return gate;
        }

        gate.setId(this.gateId+=1);
        gate.setCreatedAt(new Date());
        gate.setUpdatedAt(new Date());
        gateMap.put(gate.getId(),gate);
        return gate;
    }


}
