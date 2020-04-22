package com.bridgelabz.parkinglot.Observer;

public class ParkingBill {
    private final double COST_PER_HOUR = 1.5;
    private static int arrivingHour;
    private static int departingHour;

    public void arrivingHour(int arrivingHour) {
        this.arrivingHour = arrivingHour;
    }

    public void departureHour(int departingHour) {
        this.departingHour = departingHour;
    }

    public double generateParkingBill() {
        return (this.departingHour - this.arrivingHour) * COST_PER_HOUR;
    }
}