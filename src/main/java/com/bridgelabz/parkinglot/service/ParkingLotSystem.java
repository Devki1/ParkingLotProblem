package com.bridgelabz.parkinglot.service;

import com.bridgelabz.parkinglot.Observer.ParkingAttendant;
import com.bridgelabz.parkinglot.Observer.ParkingLotObserver;
import com.bridgelabz.parkinglot.Observer.ParkingLotException;
import com.bridgelabz.parkinglot.Observer.Subject;

import java.util.*;

public class ParkingLotSystem implements Subject {

    public int parkingLotSize;
    public String vehicleName;
    public HashMap<Integer, String> parkingLot;
    private List<ParkingLotObserver> observers = new ArrayList<ParkingLotObserver>();
    ParkingAttendant parkingAttendant = new ParkingAttendant(5);

    public ParkingLotSystem(int parkingLotSize) {
        this.parkingLotSize = parkingLotSize;
        this.parkingLot = new HashMap<>();
        for (int itr = 1; itr <= parkingLotSize; itr++) {
            parkingLot.put(itr, null);
        }
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
            observer.sendParkingStatus(parkingLot);
        }
    }


    public void park(String vehicle) throws ParkingLotException {
        this.vehicleName = vehicle;
        parkingLot = parkingAttendant.park(vehicleName, parkingLot);
        this.notifyObservers();
    }

    public boolean unPark(String vehicle) throws ParkingLotException {
        if (!isVehiclePresentInLot(vehicle))
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE, "VEHICLE IS ALREADY UNPARKED");
        Iterator<String> parkingLotIterator = getParkingLotIterator(parkingLot);
        this.vehicleName = vehicle;
        while (parkingLotIterator.hasNext()) {
            if (parkingLotIterator.next().equals(vehicle)) {
                parkingLotIterator.remove();
                return true;
            }
        }
        this.notifyObservers();
        return true;
    }

    private boolean isVehiclePresentInLot(String vehicle) {
        Iterator<String> parkingLotIterator = getParkingLotIterator(parkingLot);
        while (parkingLotIterator.hasNext()) {
            if (Objects.equals(parkingLotIterator.next(), vehicle))
                return true;
        }
        return false;
    }

    private Iterator<String> getParkingLotIterator(HashMap<Integer, String> parkingLot) {
        return parkingLot.values().iterator();
    }

    public boolean isVehicleParked() {
        return parkingLot.containsValue(vehicleName);
    }

    public static boolean isParkingLotFull(HashMap<Integer, String> parkingLot) {
        for (int iteration = 1; iteration <= parkingLot.size(); iteration++) {
            if (parkingLot.get(iteration) == null)
                return false;
        }
        return true;
    }
}