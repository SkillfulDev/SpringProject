package ua.chernonog.springcourse.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Person {

    private int id;
    @NotNull(message = "Эта графа не должна быть пустой")
    @Pattern(regexp = "[А-Я]\\W+\\s[А-Я]\\W+\\s[А-Я]\\W+",
            message = "Полное имя пользователя должно быть в формате Фамилия Имя Отчество")
    private String fullName;
    @NotNull
    @Min(value = 1920, message = "Минимальный год 1920")
    @Max(value = 2023, message = "Год рождения не должен привышать текущий год")
    private int year;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
