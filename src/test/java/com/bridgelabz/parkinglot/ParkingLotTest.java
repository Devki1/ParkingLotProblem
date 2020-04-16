package com.bridgelabz.parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;

    @Before
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem();
        vehicle = new Object();
    }

    @Test
    public void givenOwnersVehicle_WhenParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle);
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenOwnersVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
            Assert.assertEquals(false, isParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL, e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void givenOwnersVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.unPark(vehicle);
            boolean isUnparked = parkingLotSystem.isVehicleUnparked(vehicle);
            Assert.assertTrue(isUnparked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_LOT_EMPTY, e.type);
        }
    }
}