package com.bridgelabz.Observer;
public interface Subject {
    public void register(ParkingLotObserver obj);
    public void unRegister(ParkingLotObserver obj);
    public void notifyObservers(int size);
}