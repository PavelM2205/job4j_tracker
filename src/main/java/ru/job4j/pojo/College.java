package ru.job4j.pojo;

import java.time.LocalDate;
import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setNameSurnamePatronymic("Ivanov Ivan Ivanovich");
        student.setGroup("II");
        student.setEnter(LocalDate.of(2020, 5, 23));

        System.out.println(student.getNameSurnamePatronymic() + " группа номер: "
        + student.getGroup() + ". Дата поступления: " + student.getEnter());
    }
}
