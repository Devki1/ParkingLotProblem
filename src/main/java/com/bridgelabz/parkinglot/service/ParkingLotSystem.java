package com.bridgelabz.parkinglot.service;

import com.bridgelabz.parkinglot.Observer.ParkingLotObserver;
import com.bridgelabz.parkinglot.Observer.ParkingLotException;
import com.bridgelabz.parkinglot.Observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem implements Subject {

    private int parkingLotCapacity;
    private String vehicleName;
    private List<String> parkingLot;
    private List<ParkingLotObserver> observers = new ArrayList<ParkingLotObserver>();

    public ParkingLotSystem(int parkingLotCapacity) {
        this.parkingLotCapacity = parkingLotCapacity;
        this.parkingLot = new ArrayList<String>();
    }

    @Override
    public void register(ParkingLotObserver o) {
        observers.add(o);
    }

    @Override
    public void unRegister(ParkingLotObserver o) {
        observers.remove(observers.indexOf(o));
    }

    @Override
    public void notifyObservers() {
        for (ParkingLotObserver observer : observers) {
            observer.sendParkingStatus(parkingLot.size(), parkingLotCapacity);
        }
    }


    public void park(String vehicle) throws ParkingLotException {
        if (parkingLot.size() >= parkingLotCapacity)
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_FULL, "PARKING LOT IS FULL");
        this.vehicleName = vehicle;
        parkingLot.add(vehicleName);
        this.notifyObservers();
    }

    public void unPark(String vehicle) throws ParkingLotException {
        if (!(parkingLot.contains(vehicle)))
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE, "VEHICLE IS ALREADY UNPARKED");
        parkingLot.remove(vehicle);
        this.notifyObservers();
    }

    public boolean isVehicleParked() {
        return parkingLot.contains(vehicleName);
    }
}