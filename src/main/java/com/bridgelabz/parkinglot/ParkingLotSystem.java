package com.bridgelabz.parkinglot;

import com.bridgelabz.parkinglot.service.ParkingLotException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {
    private int parkingLotSize;
    private String vehicleName;
    private List<String> parkingLot;

    public ParkingLotSystem(int parkingLotSize) {
        this.parkingLotSize = parkingLotSize;
        this.parkingLot = new ArrayList<String>();
    }

    public void park(String vehicle) throws ParkingLotException {
        if (parkingLot.size() >= parkingLotSize)
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_FULL, "PARKING LOT IS FULL");
        this.vehicleName = vehicle;
        parkingLot.add(vehicleName);
    }

    public void unPark(String vehicle) throws ParkingLotException {
        if (!(parkingLot.contains(vehicle)))
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE, "VEHICLE IS ALREADY UNPARKED");
        parkingLot.remove(vehicle);
    }

    public boolean isVehicleParked() {
        if (parkingLot.contains(vehicleName))
            return true;
        return false;
    }
}
















































































































































