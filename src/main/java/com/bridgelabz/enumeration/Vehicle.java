package com.bridgelabz.enumeration;

import java.util.Objects;

public class Vehicle {
    public String name;
    public String brand;
    public String colour;

    public Vehicle(String name, String brand, String colour) {
        this.name = name;
        this.brand = brand;
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", colour='" + colour + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(name, vehicle.name) &&
                Objects.equals(brand, vehicle.brand) &&
                Objects.equals(colour, vehicle.colour);
    }
}
