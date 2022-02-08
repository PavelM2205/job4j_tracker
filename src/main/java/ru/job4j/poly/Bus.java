package ru.job4j.poly;

public class Bus implements Transport {

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
}
