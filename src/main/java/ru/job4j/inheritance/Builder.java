package ru.job4j.inheritance;

public class Builder extends Engineer {

    private String speciality;

    public Builder(String name, String surname, String education, String birthday,
                   String driversLicence, String speciality) {
        super(name, surname, education, birthday, driversLicence);
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void buildHouse(int fund) {
        if (fund >= 1_000_000) {
            System.out.println("Я посторою этот дом");
        } else {
            System.out.println("Вам хватит только на шалаш");
        }
    }
}
