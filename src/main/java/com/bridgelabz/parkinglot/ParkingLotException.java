package com.bridgelabz.parkinglot;

public class ParkingLotException extends Exception {
//    private final String message;
  //  private final ExceptionType type;

    public enum ExceptionType {
        PARKING_LOT_IS_FULL,PARKING_LOT_EMPTY;
    }

    public ExceptionType type;

    public ParkingLotException(ExceptionType type, String message) {
        super(message);
        this.type = type;

    }
}
