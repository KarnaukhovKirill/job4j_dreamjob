package ru.job4j.dream.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Post {
    private int id;
    private String name;
    private String description;
    private Calendar created;
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public Post(int id, String name, Calendar created, String description) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.description = description;
    }

    public Post(int id, String name, Calendar created) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.description = "";
    }

    public Post(int id, String name) {
        this.id = id;
        this.name = name;
        this.created = Calendar.getInstance();
        this.description =  "";
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return dateFormat.format(created.getTime());
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Calendar getCalendar() {
        return created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
