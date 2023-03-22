package ua.chernonog.springcourse.model;

import jakarta.validation.constraints.Pattern;

public class Person {
    private int id;
    @Pattern(regexp = "[А-Я]\\W+\\s[А-Я]\\W+\\s[А-Я]\\W+", message = "Полное имя пользователя должно быть в формате Имя Отчество Фамилия")
    private String fullName;

    private int age;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
