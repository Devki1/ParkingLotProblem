package com.bridgelabz.parkinglot;

public class ParkingLotSystem {
    private Object vehicle;

    public boolean park(Object vehicle) {
        this.vehicle = vehicle;
        return true;
    }

    public boolean unPark(Object vehicle) {
        if (vehicle.equals(this.vehicle)) {
            this.vehicle = null;
            return true;
        }
        return false;
    }
}
