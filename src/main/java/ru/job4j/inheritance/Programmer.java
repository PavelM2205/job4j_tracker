package ru.job4j.inheritance;

public class Programmer extends Engineer {

    private String language;

    public Programmer(String name, String surname, String education, String birthday,
                    String driversLicence, String language) {
        super(name, surname, education, birthday, driversLicence);
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void madeResum() {
        System.out.println("Name: " + getName());
        System.out.println("Surname: " + getSurname());
        System.out.println("Education: " + getEducation());
        System.out.println("Birthday: " + getBirthday());
        System.out.println("DriversLicense: " + getDriversLicence());
        System.out.println("Language: " + getLanguage());
    }
}
