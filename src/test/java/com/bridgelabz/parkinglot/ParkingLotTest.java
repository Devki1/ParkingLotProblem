package com.bridgelabz.parkinglot;

import com.bridgelabz.parkinglot.Observer.AirportSecurity;
import com.bridgelabz.parkinglot.Observer.ParkingLotException;
import com.bridgelabz.parkinglot.Observer.ParkingLotOwner;
import com.bridgelabz.parkinglot.service.ParkingLotSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        parkingLotSystem.park("Tata Indigo CS");
        boolean isVehicleParked = parkingLotSystem.isVehicleParked();
        Assert.assertTrue(isVehicleParked);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws  ParkingLotException {
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
    public void givenAVehicle_WhenAlreadyUnParkedAndTriedToUnParkAgain_ShouldThrowException() {
        try {
            parkingLotSystem.park("Tata Indigo CS");
            parkingLotSystem.unPark("Tata Indigo CS");
            parkingLotSystem.unPark("Tata Indigo CS");
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.VEHICLE_ALREADY_UNPARKED_OR_WRONG_VEHICLE, e.type);
        }
    }

    @Test
    public void givenParkingLotIsFull_OwnerShouldShowFullSign() throws ParkingLotException {
        parkingLotSystem.register(owner);
        parkingLotSystem.park("Tata Indigo CS");
        parkingLotSystem.park("Toyota Fortuner");
        parkingLotSystem.park("Maruti Swift Dzire");
        parkingLotSystem.park("Tata Hexa");
        parkingLotSystem.park("Maruti 800");
        Assert.assertEquals(owner.getFlag(), ParkingLotOwner.Flag.PARKING_IS_FULL);
    }

    @Test
    public void givenParkingLotIsNotFull_OwnerShouldShowVacantSign() throws ParkingLotException {
        parkingLotSystem.register(owner);
        parkingLotSystem.park("Tata Indigo CS");
        parkingLotSystem.park("Toyota Fortuner");
        parkingLotSystem.park("Maruti Swift Dzire");
        parkingLotSystem.park("Tata Hexa");
        Assert.assertEquals(owner.getFlag(), ParkingLotOwner.Flag.PARKING_IS_VACANT);
    }

    @Test
    public void givenParkingLotIsFull_SecurityStaffShouldBeUpdated() throws ParkingLotException {
        parkingLotSystem.register(airportSecurity);
        parkingLotSystem.park("Tata Indigo CS");
        parkingLotSystem.park("Toyota Fortuner");
        parkingLotSystem.park("Maruti Swift Dzire");
        parkingLotSystem.park("Tata Hexa");
        parkingLotSystem.park("Maruti 800");
        Assert.assertEquals(true, airportSecurity.isParkingFull());
    }

    @Test
    public void givenParkingLotIsNotFull_SecurityStaffShouldBeUpdated() throws ParkingLotException {
        parkingLotSystem.register(airportSecurity);
        parkingLotSystem.park("Tata Indigo CS");
        parkingLotSystem.park("Toyota Fortuner");
        parkingLotSystem.park("Maruti Swift Dzire");
        Assert.assertFalse(airportSecurity.isParkingFull());
    }
}