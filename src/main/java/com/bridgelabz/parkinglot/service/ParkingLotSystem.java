package com.bridgelabz.parkinglot.service;
import com.bridgelabz.parkinglot.Observer.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ParkingLotSystem implements Subject {
    ArrayList<ParkingLotObserver> observers = new ArrayList<ParkingLotObserver>();
    ParkingAttendant attendant;
    ParkingBill parkingBill = new ParkingBill();
    int count = 0;
    ParkingLot parkingLot;
    int lotCapacity;
    int lotSize;
    HashMap<Integer, HashMap> lotMaps = new HashMap<Integer, HashMap>();

    public ParkingLotSystem(int lotCapacity, int lotSize) {
        this.lotSize = lotSize;
        this.lotCapacity = lotCapacity;
        parkingLot = new ParkingLot(lotCapacity);
        for (int i = 1; i <= lotSize; i++) {
            HashMap<Integer, Object> map = parkingLot.getEmptyParkingLot();
            lotMaps.put(i, map);
        }
        attendant = new ParkingAttendant();
    }

    public void register(ParkingLotObserver obj) {
        observers.add(obj);
    }

    @Override
    public void notifyObservers() {
        for (Iterator<ParkingLotObserver> it =
             observers.iterator(); it.hasNext(); ) {
            ParkingLotObserver o = it.next();
            o.sendParkingMessage(count, this.lotCapacity);
        }
    }



    public void parkVehicle(Object vehicle, int arrivingHour) throws ParkingLotException {
        if (count >= lotCapacity * lotSize)
            throw new ParkingLotException("Parking lot is full.",
                    ParkingLotException.ExceptionType.NO_PARKING_AVAILABLE);
        AtomicBoolean vehicleCheck = new AtomicBoolean(false);
        lotMaps.values().stream().forEach(hashMap -> {
            if (hashMap.containsValue(vehicle)) {
                vehicleCheck.set(true);
            }
        });
        if (vehicleCheck.get())
            throw new ParkingLotException("Vehicle id already present",
                    ParkingLotException.ExceptionType.VEHICLE_ALREADY_PRESENT);
        lotMaps = attendant.parkVehicle(vehicle, lotMaps);
        count++;
        parkingBill.arrivingHour(arrivingHour);
        this.notifyObservers();
    }

    public boolean isVehicleParked(Object vehicle) {
        for (HashMap map : lotMaps.values()) {
            if (map.containsValue(vehicle))
                return true;
        }
        return false;
    }

    public boolean unParkVehicle(Object vehicle, Integer parkingSlot, Integer parkingLotNumber, int departingHour) {
        if (lotMaps.get(parkingLotNumber).containsValue(vehicle)) {
            lotMaps.get(parkingLotNumber).put(parkingSlot, null);
            count--;
            parkingBill.departureHour(departingHour);
            this.notifyObservers();
            return true;
        }
        return false;
    }
}