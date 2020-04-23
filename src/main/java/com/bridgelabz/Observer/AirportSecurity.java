package com.bridgelabz.Observer;
public class AirportSecurity implements ParkingLotObserver {
    boolean isParkingFull;
    public boolean isParkingFull() {

        return isParkingFull;
    }
    @Override
    public void sendParkingStatus(int currentlyOccupiedSlots, int parkingLotCapacity) {
        isParkingFull = (currentlyOccupiedSlots == parkingLotCapacity) ? true : false;
    }
}