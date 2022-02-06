package ru.job4j.inheritance;

public class Engineer extends Profession {

    private String driversLicence;

    public Engineer(String name, String surname, String education, String birthday,
                    String driversLicence) {
        super(name, surname, education, birthday);
        this.driversLicence = driversLicence;
    }

    public String getDriversLicence() {
        return driversLicence;
    }

    public ElectricalSafetyLevel returnLevel() {
        ElectricalSafetyLevel lev = new ElectricalSafetyLevel("III");
        return lev;
    }
}
