package com.bridgelabz.parkinglot.Observer;

public interface ParkingLotObserver {
    public void sendParkingStatus(int currentOccupiedSlots, int parkingLotCapacity);
}

