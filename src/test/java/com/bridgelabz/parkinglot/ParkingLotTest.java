package com.bridgelabz.parkinglot;

import com.bridgelabz.parkinglot.service.ParkingLotException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;

    @Before
    public void setUp() {
        parkingLotSystem = new ParkingLotSystem(5);
        vehicle = new Object();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park("Tata Indigo CS");
        boolean isVehicleParked = parkingLotSystem.isVehicleParked();
        Assert.assertTrue(isVehicleParked);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park("Toyota Fortuner");
        parkingLotSystem.unPark("Toyota Fortuner");
        boolean isVehicleUnParked = parkingLotSystem.isVehicleParked();
        Assert.assertFalse(isVehicleUnParked);
    }

    @Test
    public void givenAWrongVehicle_WhenTriedToUnPark_ShouldThrowException() {

        try {
            parkingLotSystem.park("Tata Indigo CS");
            parkingLotSystem.unPark("Toyota Fortuner");
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE, e.type);
        }
    }

    @Test
    public void givenManyVehicles_WhenParkingLotSizeIsFull_ShouldThrowException() {
        try {
            parkingLotSystem.park("Tata Indigo CS");
            parkingLotSystem.park("Toyota Fortuner");
            parkingLotSystem.park("Maruti Swift Dzire");
            parkingLotSystem.park("Tata Hexa");
            parkingLotSystem.park("Maruti 800");
            parkingLotSystem.park("Suzuki Nexa");
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_LOT_FULL, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyUnParkedAndTriedToUnParkAgain_ShouldThrowException() throws ParkingLotException {
        try {
            parkingLotSystem.park("Tata Indigo CS");
            parkingLotSystem.unPark("Tata Indigo CS");
            parkingLotSystem.unPark("Tata Indigo CS");
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE, e.type);
        }
    }
}