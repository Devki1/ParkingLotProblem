package com.bridgelabz.parkinglot.Observer;

public class AirportSecurity implements ParkingLotObserver {
    boolean parkingIsFull;

    @Override
    public void sendParkingMessage(int currentVehicleCount, int lotCapacity) {
        if (currentVehicleCount >= lotCapacity)
            parkingIsFull = true;
    }

    public boolean redirectSecurityStaff() {
        return parkingIsFull;
    }
}
