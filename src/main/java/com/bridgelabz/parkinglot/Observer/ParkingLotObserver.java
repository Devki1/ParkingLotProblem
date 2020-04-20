package com.bridgelabz.parkinglot.Observer;

import java.util.HashMap;

public interface ParkingLotObserver {
    public void sendParkingStatus(HashMap<Integer, String> parkingLot);
}

