package com.bridgelabz.service;

import com.bridgelabz.Observer.AirportSecurity;
import com.bridgelabz.Observer.ParkingLotException;
import com.bridgelabz.Observer.ParkingLotOwner;
import com.bridgelabz.entity.DriverType;
import com.bridgelabz.entity.Slot;
import com.bridgelabz.entity.Vehicle;
import com.bridgelabz.entity.VehicleType;
import com.bridgelabz.utility.ParkingAttendant;
import com.bridgelabz.utility.ParkingLotSystemUtilities;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public void park(Vehicle vehicle, DriverType driverType, VehicleType vehicleType) throws ParkingLotException {
        vehicleData = parkingAttendant.park(vehicle, driverType, vehicleType);
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

    public int getWhiteCars() {
        int count = 0;
        for (Map.Entry<Slot, Vehicle> entry : vehicleData.entrySet()) {
            if (entry.getValue().colour.equals("White")) {
                count++;
            }
        }
        return count;
    }

    public int getBlueCars() {
        ArrayList blueCars = new ArrayList();
        for (Map.Entry<Slot, Vehicle> entry : vehicleData.entrySet()) {
            if (entry.getValue().colour.equals("Blue")) {
                blueCars.add(entry);
            }
        }
        return blueCars.size();
    }
}