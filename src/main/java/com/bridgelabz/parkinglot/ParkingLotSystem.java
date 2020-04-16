package com.bridgelabz.parkinglot;

public class ParkingLotSystem {
    private Object vehicle;

    public boolean park(Object vehicle) throws ParkingLotException {
        if (this.vehicle != null)
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL,"Parking lot is full");
        this.vehicle = vehicle;
         return true;
    }

    public boolean unPark(Object vehicle) {
        if (vehicle.equals(this.vehicle)) {
            this.vehicle = null;
            return true;
        }
        return false;
    }
}
