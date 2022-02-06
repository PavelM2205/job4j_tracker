package ru.job4j.inheritance;

public class Doctor extends Profession {

    private String academicDegree;

    public Doctor(String name, String surname, String education, String birthday,
                  String academicDegree) {
        super(name, surname, education, birthday);
        this.academicDegree = academicDegree;
    }

    public String getAcademicDegree() {
        return academicDegree;
    }

    public Residency returnResidency() {
        Residency res = new Residency("Medical Centre");
        return res;
    }
}
