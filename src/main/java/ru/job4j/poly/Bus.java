package ru.job4j.poly;

public class Bus implements Transport, Vehicle {

    @Override
    public void drive() {
        System.out.println("Vrum - vrum");
    }

    @Override
    public void passengers(int passengers) {
        System.out.println("Transport contains: " + passengers);
    }

    @Override
    public double refuel(double fuel) {
        return fuel * 51.7;
    }

    @Override
    public void move() {
        System.out.println("Автобус движется по скоростным трасса");
    }
}
