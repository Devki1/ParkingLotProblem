package com.bridgelabz.parkinglot.Observer;

public interface Subject {
    public void register(ParkingLotObserver o);
    public void unRegister(ParkingLotObserver o);
    public void notifyObservers();
}
