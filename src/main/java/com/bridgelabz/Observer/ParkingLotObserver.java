package com.bridgelabz.Observer;
public interface ParkingLotObserver {
    public void sendParkingStatus(int currentlyOccupiedSlots, int parkingLotCapacity);
}