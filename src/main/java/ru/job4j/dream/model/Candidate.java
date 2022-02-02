package ru.job4j.dream.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class Candidate {
    private int id;
    private String name;
    private int cityId;
    private String city;
    private Timestamp created;
    private String outputTime;
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public Candidate() {
    }

    public Candidate(int id, String name) {
        this.id = id;
        this.name = name;
        this.created = new Timestamp(System.currentTimeMillis());
    }

    public Candidate(int id, String name, int cityId) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
        this.created = new Timestamp(System.currentTimeMillis());
    }

    public Candidate(String name, int cityId, Timestamp created) {
        this.name = name;
        this.cityId = cityId;
        this.created = created;
    }

    public Candidate(int id, String name, String city, Timestamp created) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.created = created;
    }

    public Candidate(int id, String name, int cityId, Timestamp created) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
        this.created = created;
    }

    public Candidate(int id, String name, int cityId, String city, Timestamp created) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
        this.city = city;
        this.created = created;
    }

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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOutputTime() {
        return dateFormat.format(created.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id
                && Objects.equals(name, candidate.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cityId);
    }
}
