package com.bridgelabz.Observer;
public class ParkingLotException extends Exception {
    public enum ExceptionType {
        PARKING_LOT_FULL, VEHICLE_NOT_PRESENT;
    }
    public ExceptionType type;

    public ParkingLotException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
















