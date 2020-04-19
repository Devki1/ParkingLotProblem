package com.bridgelabz.parkinglot.Observer;

public class AirportSecurity  implements ParkingLotObserver {

    boolean isParkingFull;

    public boolean isParkingFull() {
        return isParkingFull;
    }

    @Override
    public void sendParkingStatus(int currentOccupiedSlots, int parkingLotCapacity) {
        isParkingFull = (currentOccupiedSlots >= parkingLotCapacity)? true : false;
    }
}