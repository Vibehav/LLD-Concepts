package org.concepts.model;

import org.concepts.enums.ParkingLotStatus;
import org.concepts.strategy.AllocationStrategy.SlotAllocationStrategyFactory;
import org.concepts.strategy.PricingStrategy;

import java.util.List;

public class ParkingLot extends BaseEntity {

    private List<ParkingFloor> floors;
    private String address;
    private List<Gate> gates;
    private ParkingLotStatus parkingLotStatus;

    // Strategy Objects
    private PricingStrategy pricingStrategy;
    private SlotAllocationStrategyFactory slotAllocationStrategyFactory;

    private ParkingLot() {
    }

    private ParkingLot(Builder builder) {
        this.floors = builder.floors;
        this.address = builder.address;
        this.gates = builder.gates;
        this.parkingLotStatus = builder.parkingLotStatus;
        this.pricingStrategy = builder.pricingStrategy;
        this.slotAllocationStrategyFactory = builder.slotAllocationStrategyFactory;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<ParkingFloor> floors;
        private String address;
        private List<Gate> gates;
        private ParkingLotStatus parkingLotStatus;
        private PricingStrategy pricingStrategy;
        private SlotAllocationStrategyFactory slotAllocationStrategyFactory;

        public Builder floors(List<ParkingFloor> floors) {
            this.floors = floors;
            return this;
        }
        public Builder address(String address) {
            this.address = address;
            return this;
        }
        public Builder gates(List<Gate> gates) {
            this.gates = gates;
            return this;
        }
        public Builder parkingLotStatus(ParkingLotStatus parkingLotStatus) {
            this.parkingLotStatus = parkingLotStatus;
            return this;
        }
        public Builder pricingStrategy(PricingStrategy pricingStrategy) {
            this.pricingStrategy = pricingStrategy;
            return this;
        }
        public Builder slotAllocationStrategyFactory(SlotAllocationStrategyFactory slotAllocationStrategyFactory) {
            this.slotAllocationStrategyFactory = slotAllocationStrategyFactory;
            return this;
        }

        public ParkingLot build() {
            //validate
            return new ParkingLot(this);
        }
    }

    // Getters

    public List<ParkingFloor> getFloors() {
        return floors;
    }
    public String getAddress() {
        return address;
    }
    public List<Gate> getGates() {
        return gates;
    }
    public ParkingLotStatus getParkingLotStatus() {
        return parkingLotStatus;
    }
    public PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }
    public SlotAllocationStrategyFactory getSlotAllocationStrategyFactory() {
        return slotAllocationStrategyFactory;
    }

    // Setters (optional)
    // You can remove these if you want ParkingLot to be immutable.
    public void setFloors(List<ParkingFloor> floors) {
        this.floors = floors;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setGates(List<Gate> gates) {
        this.gates = gates;
    }
    public void setParkingLotStatus(ParkingLotStatus parkingLotStatus) {
        this.parkingLotStatus = parkingLotStatus;
    }
    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }
    public void setSlotAllocationStrategyFactory(SlotAllocationStrategyFactory slotAllocationStrategyFactory) {
        this.slotAllocationStrategyFactory = slotAllocationStrategyFactory;
    }
}