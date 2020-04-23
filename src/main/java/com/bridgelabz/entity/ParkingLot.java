package com.bridgelabz.entity;

public class ParkingLot {
    public int lotID = 0;

    public ParkingLot(int lotId) {
        this.lotID = lotId;
    }


    @Override
    public String toString() {
        return "lotID=" + lotID;
    }
}