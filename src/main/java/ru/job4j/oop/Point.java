package ru.job4j.oop;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point that) {
        return sqrt(pow((this.x - that.x), 2) + pow((this.y - that.y), 2));
    }

    public static void main(String[] args) {
        Point a = new Point(1, 0);
        Point b = new Point(1, 0);
        Point c = new Point(3, 1);

        System.out.println(a.distance(b));
        System.out.println(b.distance(c));
        System.out.println(c.distance(a));
    }
}
