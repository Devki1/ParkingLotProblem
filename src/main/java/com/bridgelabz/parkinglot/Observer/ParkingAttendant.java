package com.bridgelabz.parkinglot.Observer;

import com.bridgelabz.parkinglot.service.ParkingLotSystem;

import java.util.HashMap;

public class ParkingAttendant {

     ParkingLotOwner owner = new ParkingLotOwner();
    int parkingLotSize = 0;

    public ParkingAttendant(int parkingLotSize) {
        this.parkingLotSize = parkingLotSize;
    }

    public HashMap<Integer, String> park(String vehicle, HashMap<Integer, String> parkingLot) throws ParkingLotException {
        Integer emptyParkingSlot = owner.getEmptyParkingSlot(parkingLot);
        if (ParkingLotSystem.isParkingLotFull(parkingLot))
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_FULL, "PARKING LOT IS FULL");
        parkingLot.put(emptyParkingSlot, vehicle);
        return parkingLot;
    }
}