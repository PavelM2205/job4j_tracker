package ru.job4j.pojo;

import java.time.LocalDate;

public class Student {

    private String nameSurnamePatronymic;
    private String group;
    private LocalDate enter;

    public String getNameSurnamePatronymic() {
        return nameSurnamePatronymic;
    }

    public void setNameSurnamePatronymic(String nameSurnamePatronymic) {
        this.nameSurnamePatronymic = nameSurnamePatronymic;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public LocalDate getEnter() {
        return enter;
    }

    public void setEnter(LocalDate enter) {
        this.enter = enter;
    }
}
