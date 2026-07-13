package org.concepts.model;

import org.concepts.enums.ParkingLotStatus;
import org.concepts.strategy.AllocationStrategy.SlotAllocationStrategyFactory;
import org.concepts.strategy.PricingStrategy;
import org.concepts.strategy.AllocationStrategy.SlotAllocationStrategy;


import java.util.List;

public class ParkingLot extends BaseEntity {

    private List<ParkingFloor> floors;
    private String address;
    private List<Gate> gates;
    private ParkingLotStatus parkingLotStatus;

    // PricingStrategy
    // Allocation Strategy
    private PricingStrategy pricingStrategy;
    private SlotAllocationStrategyFactory slotAllocationStrategyFactory;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Gate> getGates() {
        return gates;
    }

    public void setGates(List<Gate> gates) {
        this.gates = gates;
    }

    public ParkingLotStatus getParkingLotStatus() {
        return parkingLotStatus;
    }

    public void setParkingLotStatus(ParkingLotStatus parkingLotStatus) {
        this.parkingLotStatus = parkingLotStatus;
    }

    public List<ParkingFloor> getFloors() {
        return floors;
    }

    public void setFloors(List<ParkingFloor> floors) {
        this.floors = floors;
    }

    public PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public SlotAllocationStrategyFactory getSlotAllocationStrategyFactory() {
        return slotAllocationStrategyFactory;
    }

    public void setSlotAllocationStrategyFactory(SlotAllocationStrategyFactory slotAllocationStrategyFactory) {
        this.slotAllocationStrategyFactory = slotAllocationStrategyFactory;
    }
}
/**
 * Schema Design
 * parking_lot
 * id - primitve
 * address - primitve
 *
 */