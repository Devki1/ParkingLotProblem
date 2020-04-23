package com.bridgelabz.utility;

public class ParkingLotSystemUtilities {

public int parkingLotCapacity = 0;
    public static int parkingLotNumber = 0;
    public static int numberOfParkingLots = 0;

    public ParkingLotSystemUtilities(int parkingLotCapacity, int noOfParkingLots) {
        this.numberOfParkingLots = noOfParkingLots;
        this.parkingLotCapacity = parkingLotCapacity;
    }

    public int getNoOfSlotsPerLot() {
        return parkingLotCapacity / numberOfParkingLots;
    }

    public static int assignLot(int slotID) {
        switch (slotID % numberOfParkingLots) {
            case 0 :
                parkingLotNumber = 4;
                break;
            case 1 :
                parkingLotNumber = 1;
                break;
            case 2 :
                parkingLotNumber = 2;
                break;
            case 3 :
                parkingLotNumber = 3;
        }
        return parkingLotNumber;
    }
}
