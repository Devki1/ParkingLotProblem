package com.bridgelabz.Observer;
public class ParkingLotOwner implements ParkingLotObserver {
    public enum Sign {PARKING_IS_VACANT, PARKING_IS_FULL};

        Sign sign;

        public Sign getSign() {
            return sign;
        }

        @Override
        public void sendParkingStatus(int currentlyOccupiedSlots, int parkingLotCapacity) {
            sign = (currentlyOccupiedSlots == parkingLotCapacity) ? Sign.PARKING_IS_FULL : Sign.PARKING_IS_VACANT;
        }
    }
