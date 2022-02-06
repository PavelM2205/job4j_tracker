package ru.job4j.inheritance;

public class Dentist extends Doctor {

    private int removedTeeth;

    public Dentist(String name, String surname, String education, String birthday,
                   String academicDegree, int removedTeeth) {
        super(name, surname, education, birthday, academicDegree);
        this.removedTeeth = removedTeeth;
    }

    public int getRemovedTeeth() {
        return removedTeeth;
    }

    public Appointment returnAppointment(String date) {
        Appointment appoint = new Appointment(date);
        return appoint;
    }
}
