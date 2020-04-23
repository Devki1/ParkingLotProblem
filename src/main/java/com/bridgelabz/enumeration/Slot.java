package com.bridgelabz.enumeration;

import java.time.LocalTime;

public class Slot {
    public int slotID;
    public LocalTime arrivalTime;
    public LocalTime departureTime;
    public ParkingLot lot;

    public int getSlotID() {
        return slotID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public ParkingLot getLot() {
        return lot;
    }

    public void setLot(ParkingLot lot) {
        this.lot = lot;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "slotID=" + slotID +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", lot=" + lot +
                '}';
    }
}