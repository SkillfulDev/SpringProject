package ua.chernonog.springcourse.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public class Person {
    private int id;
    @Pattern(regexp = "[А-Я]\\W+\\s[А-Я]\\W+\\s[А-Я]\\W+", message = "Полное имя пользователя должно быть в формате Фамилия Имя Отчество")
    private String fullName;
    @Min(value = 1920, message = "Начальный год не менее 1920 года")
    @Max(value = 2024, message = "Не должен привышать сегодняшний год")

    private int year;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
