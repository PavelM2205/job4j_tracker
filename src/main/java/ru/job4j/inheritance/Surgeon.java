package ru.job4j.inheritance;

public class Surgeon extends Doctor {

    private double death;
    private int experience;

    public Surgeon(String name, String surname, String education, String birthday,
                   String academicDegree, double death, int experience) {
        super(name, surname, education, birthday, academicDegree);
        this.death = death;
        this.experience = experience;
    }

    public double getDeath() {
        return death;
    }

    public int getExperience() {
        return experience;
    }

    public double countOfKills() {
        return death / experience;
    }

}
