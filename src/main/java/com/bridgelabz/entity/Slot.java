package com.bridgelabz.entity;

public class Slot {
    public int slotID;
    public ParkingLot lot;
    public int arrivalHour;
    public int arrivalMinute;
    public int departureHour;
    public int departureMinute;

    public int getSlotID() {
        return slotID;
    }

      public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public int getArrivalTime() {
        return arrivalHour;
    }

    public void setArrivalTime(int arrivalHour, int arrivalMinute) {
        this.arrivalHour = arrivalHour;
        this.arrivalMinute = arrivalMinute;
    }

    public int getDepartureTime() {
        return departureHour;
    }

    public void setDepartureTime(int departureHour, int departureMinute) {
        this.departureHour = departureHour;
        this.departureMinute = departureMinute;
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
                ", lot=" + lot +
                ", arrivalHour=" + arrivalHour +
                ", arrivalMinute=" + arrivalMinute +
                ", departureHour=" + departureHour +
                ", departureMinute=" + departureMinute +
                '}';
    }
}