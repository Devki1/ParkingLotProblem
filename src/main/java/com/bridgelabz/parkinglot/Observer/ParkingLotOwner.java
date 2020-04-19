package com.bridgelabz.parkinglot.Observer;

public class ParkingLotOwner implements ParkingLotObserver {
    public enum Flag {PARKING_IS_VACANT, PARKING_IS_FULL};

    Flag flag;

    public Flag getFlag() {
        return flag;
    }

    @Override
    public void sendParkingStatus(int currentOccupiedSlots, int parkingLotCapacity) {
        flag = (currentOccupiedSlots >= parkingLotCapacity) ? Flag.PARKING_IS_FULL : Flag.PARKING_IS_VACANT;
    }
}