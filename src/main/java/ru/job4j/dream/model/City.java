package ru.job4j.dream.model;

import java.util.Objects;

public class City {
    private int id;
    private String title;

    public City() {
    }

    public City(String title) {
        this.title = title;
    }

    public City(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        City city = (City) o;
        return id == city.id && Objects.equals(title, city.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
