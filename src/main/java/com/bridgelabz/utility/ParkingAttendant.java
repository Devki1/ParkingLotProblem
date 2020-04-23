package com.bridgelabz.utility;

import com.bridgelabz.Observer.ParkingLotException;
import com.bridgelabz.Observer.ParkingLotObserver;
import com.bridgelabz.Observer.ParkingLotOwner;
import com.bridgelabz.Observer.Subject;
import com.bridgelabz.enumeration.Slot;
import com.bridgelabz.enumeration.Vehicle;
import com.bridgelabz.parkinglot.Observer.ParkingLot;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ParkingAttendant implements Subject {
    public int parkingLotCapacity = 0;
    public int noOfParkingLots = 0;
    public int noOfSlotsPerLot = 0;
    public int slotCounter = 0;
    private List<ParkingLotObserver> observers = new ArrayList<ParkingLotObserver>();
    public HashMap<Slot, Vehicle> vehicleData;

    public ParkingAttendant(int parkingLotCapacity, int noOfParkingLots, int noOfSlotsPerLot) {
        this.parkingLotCapacity = parkingLotCapacity;
        this.noOfParkingLots = noOfParkingLots;
        this.noOfSlotsPerLot = noOfSlotsPerLot;
        this.vehicleData = new HashMap<>();
    }

    public void register(ParkingLotOwner obj) {
        observers.add(obj);
    }

    @Override
    public void register(ParkingLotObserver obj) {

    }

    @Override
    public void unRegister(ParkingLotObserver obj) {
        observers.remove(observers.indexOf(obj));
    }

    @Override
    public void notifyObservers(int currentlyOccupiedSlots) {
        for (ParkingLotObserver observer : observers) {
            observer.sendParkingStatus(currentlyOccupiedSlots, this.parkingLotCapacity);
        }
    }

    public HashMap<Slot, Vehicle> park(Vehicle vehicle) throws ParkingLotException {
        if (vehicleData.size() > this.parkingLotCapacity)
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_FULL, "PARKING LOT FULL");
        Slot slot = new Slot();
        slotCounter = slotCounter + 1;
        slot.setSlotID(slotCounter);
        slot.setArrivalTime(LocalTime.of(11, 10, 37));
        ParkingLot lot = new ParkingLot(ParkingLotSystemUtilities.assignLot(slot.getSlotID()));
        slot.setLot(lot);
        vehicleData.put(slot, vehicle);
        this.notifyObservers(vehicleData.size());
        return vehicleData;
    }

    public HashMap<Slot, Vehicle> unPark(Vehicle vehicle) throws ParkingLotException {
        if (!vehicleData.containsValue(vehicle))
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_PRESENT, "VEHICLE NOT PRESENT");
        Set<Slot> slots = vehicleData.keySet();
        for (Slot slot : slots) {
            if (vehicleData.get(slot).equals(vehicle)) {
                slot.setDepartureTime(LocalTime.of(12, 19, 56));
                vehicleData.remove(slot);
                this.notifyObservers(vehicleData.size());
            }
        }
        return vehicleData;
    }
}