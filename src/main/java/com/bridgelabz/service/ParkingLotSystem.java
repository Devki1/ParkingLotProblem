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

import java.util.HashMap;
import java.util.Map;

public class ParkingLotSystem {
    public int noOfSlotsPerLot;
    public int arrivalTime;
    ParkingAttendant parkingLotAttendant = null;
    ParkingLotSystemUtilities assignParkingLot = null;
    public HashMap<Slot, Vehicle> vehicleParkedDetail = new HashMap<Slot, Vehicle>();

    public ParkingLotSystem(int parkingLotCapacity, int noOfParkingLots) {
        this.assignParkingLot = new ParkingLotSystemUtilities(parkingLotCapacity, noOfParkingLots);
        this.noOfSlotsPerLot = assignParkingLot.getNoOfSlotsPerLot();
        this.parkingLotAttendant = new ParkingAttendant(parkingLotCapacity, noOfParkingLots, assignParkingLot.getNoOfSlotsPerLot());
    }

    public void park(Vehicle vehicle, DriverType driverType, VehicleType vehicleType) throws ParkingLotException {
        vehicleParkedDetail = parkingLotAttendant.attendantPark(vehicle, driverType, vehicleType);
    }

    public void unPark(Vehicle vehicle) throws ParkingLotException {
        vehicleParkedDetail = parkingLotAttendant.attendantUnPark(vehicle);
    }

    public void register(ParkingLotOwner owner) {
        parkingLotAttendant.register(owner);
    }

    public void register(AirportSecurity airportPersonnel) {
        parkingLotAttendant.register(airportPersonnel);
    }

    public boolean isVehiclePresentInLot(Vehicle vehicle) {
        return vehicleParkedDetail.containsValue(vehicle);
    }

    public int getArrivalTime(Vehicle vehicle) {
        for (Slot slot : vehicleParkedDetail.keySet()) {
            if (vehicleParkedDetail.get(slot).equals(vehicle)) {
                arrivalTime = slot.arrivalHour;
            }
        }
        return arrivalTime;
    }

    public int getWhiteCars() {
        int counter = 0;
        for (Map.Entry<Slot, Vehicle> entry : vehicleParkedDetail.entrySet()) {
            if (entry.getValue().colour.equals("White")) {
                counter++;
            }
        }
        return counter;
    }

    public int getBlueToyotaCars() {
        int count = 0;
        for (Map.Entry<Slot, Vehicle> entry : vehicleParkedDetail.entrySet()) {
            Vehicle value = entry.getValue();
            if (value.brand.equals("TOYOTA") && value.colour.equals("Blue")) {
                count++;
            }
        }
        return count;
    }

    public int getBmwCars() {
        int counter = 0;
        for (Map.Entry<Slot, Vehicle> entry : vehicleParkedDetail.entrySet()) {
            Vehicle value = entry.getValue();
            if (value.brand.equals("Bmw")) {
                counter++;
            }
        }
        return counter;
    }

    public int getBeforeThirtyMinuteParkedCar() {
        return parkingLotAttendant.getBeforeThirtyMinuteParkedCar();
    }
}