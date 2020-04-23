package com.bridgelabz.service;

import com.bridgelabz.Observer.AirportSecurity;
import com.bridgelabz.Observer.ParkingLotException;
import com.bridgelabz.Observer.ParkingLotObserver;
import com.bridgelabz.Observer.ParkingLotOwner;
import com.bridgelabz.enumeration.Slot;
import com.bridgelabz.enumeration.Vehicle;
import com.bridgelabz.utility.ParkingAttendant;
import com.bridgelabz.utility.ParkingLotSystemUtilities;

import java.time.LocalTime;
import java.util.HashMap;

public class ParkingLotSystem {

    public int noOfSlotsPerLot;
    public LocalTime arrivalTime;
    ParkingAttendant parkingAttendant = null;
    ParkingLotSystemUtilities parkingUtilities = null;
    public HashMap<Slot, Vehicle> vehicleData = null;

    public ParkingLotSystem(int parkingLotCapacity, int noOfParkingLots) {
        this.parkingUtilities = new ParkingLotSystemUtilities(parkingLotCapacity, noOfParkingLots);
        this.noOfSlotsPerLot = parkingUtilities.getNoOfSlotsPerLot();
        this.parkingAttendant = new ParkingAttendant(parkingLotCapacity, noOfParkingLots, parkingUtilities.getNoOfSlotsPerLot());
        this.vehicleData = new HashMap<>();
    }

    public void register(ParkingLotOwner owner) {
        parkingAttendant.register(owner);
    }

    public void register(AirportSecurity airportPersonnel) {
        parkingAttendant.register(airportPersonnel);
    }

    public boolean isVehiclePresentInLot(Vehicle vehicle) {
        return vehicleData.containsValue(vehicle);
    }

    public void park(Vehicle vehicle) throws ParkingLotException {
        vehicleData = parkingAttendant.park(vehicle);
    }

    public void unPark(Vehicle vehicle) throws ParkingLotException {
        vehicleData = parkingAttendant.unPark(vehicle);
    }

    public LocalTime getArrivalTime(Vehicle vehicle) {
        for (Slot slot : vehicleData.keySet()) {
            if (vehicleData.get(slot).equals(vehicle)) {
                arrivalTime = slot.getArrivalTime();
            }
        }
        return arrivalTime;
    }
}