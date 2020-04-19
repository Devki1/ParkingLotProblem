package com.bridgelabz.parkinglot.Observer;

public class ParkingLotException extends Throwable{
    public enum ExceptionType {
        PARKING_LOT_FULL, VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE;
    }
    public ExceptionType type;

    public ParkingLotException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}