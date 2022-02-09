package ru.job4j.strategy;

public class Square implements Shape {

    @Override
    public String draw() {
        String ln = System.lineSeparator();
        return    "_________" + ln
                + "|       |" + ln
                + "|       |" + ln
                + "|_______|";
    }

    public static void main(String[] args) {
        Square sq = new Square();
        System.out.println(sq.draw());
    }
}
