package com.bridgelabz.parkinglot;

import com.bridgelabz.parkinglot.Observer.AirportSecurity;
import com.bridgelabz.parkinglot.Observer.ParkingLotException;
import com.bridgelabz.parkinglot.Observer.ParkingLotOwner;
import com.bridgelabz.parkinglot.service.ParkingLotSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

public class ParkingLotTest {

    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;
    ParkingLotOwner owner = null;
    AirportSecurity airportSecurity = null;

    @Before
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(5);
        vehicle = new Object();
        owner = new ParkingLotOwner();
        airportSecurity = new AirportSecurity();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park("Car1");
        boolean isVehicleParked = parkingLotSystem.isVehicleParked();
        Assert.assertTrue(isVehicleParked);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park("Car2");
        boolean isVehicleUnParked = parkingLotSystem.unPark("Car2");
        Assert.assertTrue(isVehicleUnParked);
    }

    @Test
    public void givenAWrongVehicle_WhenTriedToUnPark_ShouldThrowException() {

        try {
            parkingLotSystem.park("Car1");
            parkingLotSystem.unPark("Car2");
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE, e.type);
        }
    }

    @Test
    public void givenManyVehicles_WhenParkingLotSizeIsFull_ShouldThrowException() {
        try {
            parkingLotSystem.park("Car1");
            parkingLotSystem.park("Car2");
            parkingLotSystem.park("Car3");
            parkingLotSystem.park("Car4");
            parkingLotSystem.park("Car5");
            parkingLotSystem.park("Car6");
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_LOT_FULL, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyUnParkedAndTriedToUnParkAgain_ShouldThrowException() {
        try {
            parkingLotSystem.park("Car1");
            parkingLotSystem.unPark("Car1");
            parkingLotSystem.unPark("Car1");
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE, e.type);
        }
    }

    @Test
    public void givenParkingLotIsFull_OwnerShouldShowFullSign() throws ParkingLotException {
        parkingLotSystem.register(owner);
        parkingLotSystem.park("Car1");
        parkingLotSystem.park("Car2");
        parkingLotSystem.park("Car3");
        parkingLotSystem.park("Car4");
        parkingLotSystem.park("Car5");
        Assert.assertEquals(owner.getFlag(), ParkingLotOwner.Flag.PARKING_IS_FULL);
    }

    @Test
    public void givenParkingLotIsNotFull_OwnerShouldShowVacantSign() throws ParkingLotException {
        parkingLotSystem.register(owner);
        parkingLotSystem.park("Car1");
        parkingLotSystem.park("Car2");
        parkingLotSystem.park("Car3");
        parkingLotSystem.park("Car4");
        Assert.assertEquals(owner.getFlag(), ParkingLotOwner.Flag.PARKING_IS_VACANT);
    }

    @Test
    public void givenParkingLotIsFull_SecurityStaffShouldBeUpdated() throws ParkingLotException {
        parkingLotSystem.register(airportSecurity);
        parkingLotSystem.park("Car1");
        parkingLotSystem.park("Car2");
        parkingLotSystem.park("Car3");
        parkingLotSystem.park("Car4");
        parkingLotSystem.park("Car5");
        Assert.assertEquals(true, airportSecurity.isParkingFull());
    }

    @Test
    public void givenParkingLotIsNotFull_SecurityStaffShouldBeUpdated() throws ParkingLotException {
        parkingLotSystem.register(airportSecurity);
        parkingLotSystem.park("Car1");
        parkingLotSystem.park("Car2");
        parkingLotSystem.park("Car3");
        Assert.assertFalse(airportSecurity.isParkingFull());
    }

    @Test
    public void givenParkingLotIsFull_IfItHasSpaceAgain_OwnerShouldShowVacantSign() throws ParkingLotException {
        parkingLotSystem.register(owner);
        parkingLotSystem.park("Car1");
        parkingLotSystem.park("Car2");
        parkingLotSystem.park("Car3");
        parkingLotSystem.park("Car4");
        parkingLotSystem.park("Car5");
        parkingLotSystem.unPark("Car3");
        Assert.assertEquals(owner.getFlag(), ParkingLotOwner.Flag.PARKING_IS_VACANT);
    }

    @Test
    public void givenCar_IfFoundInParkingLot_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park("Car1");
        parkingLotSystem.park("Car2");
        parkingLotSystem.park("Car3");
        boolean isPresent = parkingLotSystem.isVehiclePresentInLot("Car2");
        Assert.assertTrue(isPresent);
    }

    @Test
    public void givenCar_IfNotFoundInParkingLot_ShouldReturnFalse() throws ParkingLotException {
        parkingLotSystem.park("Car1");
        parkingLotSystem.park("Car2");
        parkingLotSystem.park("Car3");
        boolean isPresent = parkingLotSystem.isVehiclePresentInLot("Car5");
        Assert.assertFalse(isPresent);
    }

    @Test
    public void givenAVehicle_WhenParkedAndThenUnparked_ShouldReturnArrivalTimeAndDepartureTime() throws ParkingLotException {
        parkingLotSystem.park("Car1");
        parkingLotSystem.unPark("Car1");
        Assert.assertEquals(parkingLotSystem.arrivalTime, LocalTime.now());
        Assert.assertEquals(parkingLotSystem.departureTime, LocalTime.now());
    }
}
