package com.bridgelabz.parkinglot;

public class ParkingLotSystem {
    // Variable
    private Object vehicle;

    public void park(Object vehicle) throws ParkingLotException {
        if (this.vehicle != null)
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL, "Parking lot is full");
        this.vehicle = vehicle;
    }

    // This method is used for unparking lot
    public void unPark(Object vehicle) throws ParkingLotException {
        if (this.vehicle == null)
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_EMPTY, "Parking is empty");
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
        }
    }

    // This method is used for Parking vehicle
    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicle.equals(vehicle))
            return true;
        return false;
    }

    // This method is used for unparking vehicle
    public boolean isVehicleUnparked(Object vehicle) {
        if (this.vehicle != vehicle) return true;
        return false;
    }
}

