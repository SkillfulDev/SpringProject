package ua.chernonog.springcourse.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public class Book {
    private int id;
    @NotNull(message = "Поле имя не должно быть пустым")
    private String name;
    @NotNull(message = "Поле автор не должно быть пустым")
    private String author;
    @NotNull(message = "Поле год не должно быть пустым")
    @Max(value = 2023, message = "Год не должен привышать текущий")
    private int year;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
