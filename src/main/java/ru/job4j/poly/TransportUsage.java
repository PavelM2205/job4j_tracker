package ru.job4j.poly;

public class TransportUsage {
    public static void main(String[] args) {
        Vehicle airplane1  = new Airplane();
        Vehicle airplane2 = new Airplane();
        Vehicle train1 = new Train();
        Vehicle train2 = new Train();
        Vehicle bus1 = new Bus();
        Vehicle bus2 = new Bus();
        Vehicle[] vehicals = {airplane1, airplane2, train1, train2, bus1, bus2};
        for (Vehicle vehicle : vehicals) {
            vehicle.move();
        }
    }
}
