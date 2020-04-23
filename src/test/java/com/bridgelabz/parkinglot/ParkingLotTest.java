package com.bridgelabz.parkinglot;
import com.bridgelabz.Observer.AirportSecurity;
import com.bridgelabz.Observer.ParkingLotException;
import com.bridgelabz.Observer.ParkingLotOwner;
import com.bridgelabz.enumeration.Slot;
import com.bridgelabz.enumeration.Vehicle;
import com.bridgelabz.service.ParkingLotSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.Map;

public class ParkingLotTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;
    ParkingLotOwner owner = null;
    AirportSecurity airportPersonnel = null;
    Vehicle vehicle1 = new Vehicle("Indigo CS", "TATA", "Blue");
    Vehicle vehicle2 = new Vehicle("Hexa","TATA","Blue");
    Vehicle vehicle3 = new Vehicle("Fortuner","TOYOTA","Blue");
    Vehicle vehicle4 = new Vehicle("Honda city",  "HONDA", "White");
    @Before
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(100, 4);
        vehicle = new Object();
        owner = new ParkingLotOwner();
        airportPersonnel = new AirportSecurity();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park(vehicle1);
        Assert.assertTrue(parkingLotSystem.isVehiclePresentInLot(vehicle1));
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnFalse() throws ParkingLotException {
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.unPark(vehicle1);
        Assert.assertFalse(parkingLotSystem.isVehiclePresentInLot(vehicle1));
    }

    @Test
    public void givenAWrongVehicle_WhenTriedToUnPark_ShouldThrowException() {
        try {
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.unPark(vehicle2);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.VEHICLE_NOT_PRESENT, e.type);
        }
    }

    @Test
    public void givenManyVehicles_WhenParkingLotSizeIsFull_ShouldThrowException() {
        try {
            for (int i = 0; i <= 101; i++)
                parkingLotSystem.park(vehicle1);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_LOT_FULL, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyUnParkedAndTriedToUnParkAgain_ShouldThrowException() {
        try {
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.unPark(vehicle1);
            parkingLotSystem.unPark(vehicle1);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.VEHICLE_NOT_PRESENT, e.type);
        }
    }

    @Test
    public void givenParkingLotIsFull_OwnerShouldShowFullSign() throws ParkingLotException {
        parkingLotSystem.register(owner);
        for (int i = 0; i < 100; i++)
            parkingLotSystem.park(vehicle1);
        Assert.assertEquals(owner.getSign(), ParkingLotOwner.Sign.PARKING_IS_FULL);
    }

    @Test
    public void givenParkingLotIsNotFull_OwnerShouldShowVacantSign() throws ParkingLotException {
        parkingLotSystem.register(owner);
        for (int i = 0; i < 99; i++)
            parkingLotSystem.park(vehicle1);
        Assert.assertEquals(owner.getSign(), ParkingLotOwner.Sign.PARKING_IS_VACANT);
    }

    @Test
    public void givenParkingLotIsFull_SecurityStaffSholdBeUpdated() throws ParkingLotException {
        parkingLotSystem.register(airportPersonnel);
        for (int i = 0; i < 100; i++)
            parkingLotSystem.park(vehicle1);
        Assert.assertTrue(airportPersonnel.isParkingFull());
    }

    @Test
    public void givenParkingLotIsNotFull_SecurityStaffShouldBeUpdated() throws ParkingLotException {
        parkingLotSystem.register(airportPersonnel);
        for (int i = 0; i < 99; i++)
            parkingLotSystem.park(vehicle1);
        Assert.assertFalse(airportPersonnel.isParkingFull());
    }

    @Test
    public void givenParkingLotIsFull_IfItHasSpaceAgain_OwnerShouldShowVacantSign() throws ParkingLotException {
        parkingLotSystem.register(owner);
        for (int i = 0; i < 99; i++) {
            parkingLotSystem.park(vehicle1);
        }
        parkingLotSystem.unPark(vehicle1);
        Assert.assertEquals(owner.getSign(), ParkingLotOwner.Sign.PARKING_IS_VACANT);
    }

    @Test
    public void givenVehicle_IfFoundInParkingLot_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        Assert.assertTrue(parkingLotSystem.isVehiclePresentInLot(vehicle2));
    }

    @Test
    public void givenVehicle_IfNotFoundInParkingLot_ShouldReturnFalse() throws ParkingLotException {
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        Assert.assertFalse(parkingLotSystem.isVehiclePresentInLot(vehicle4));
    }

    @Test
    public void givenAVehicle_WhenParkedAndThenUnparked_ShouldReturnArrivalTimeAndDepartureTime() throws ParkingLotException {
        parkingLotSystem.park(vehicle1);
        Assert.assertEquals(parkingLotSystem.getArrivalTime(vehicle1), LocalTime.of(9, 10, 30));
    }

    @Test
    public void givenManyVehicles_WhenParkedEvenly_ShouldReturnPosition() throws ParkingLotException {
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        for (Map.Entry<Slot, Vehicle> entry : parkingLotSystem.vehicleData.entrySet()) {
            if (entry.getValue().equals(vehicle1)) {
                Assert.assertEquals(1,entry.getKey().lot.lotID);
                Assert.assertEquals(1,entry.getKey().slotID);
            }
            if (entry.getValue().equals(vehicle2)) {
                Assert.assertEquals(2,entry.getKey().lot.lotID);
                Assert.assertEquals(2,entry.getKey().slotID);
            }
            if (entry.getValue().equals(vehicle3)) {
                Assert.assertEquals(3,entry.getKey().lot.lotID);
                Assert.assertEquals(3,entry.getKey().slotID);
            }
            if (entry.getValue().equals(vehicle4)) {
                Assert.assertEquals(4,entry.getKey().lot.lotID);
                Assert.assertEquals(4,entry.getKey().slotID);
            }
        }
    }
}