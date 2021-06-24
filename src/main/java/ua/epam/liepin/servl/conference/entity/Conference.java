package ua.epam.liepin.servl.conference.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Conference {
    private int id;
    private String title;
    private String description;
    private LocalDate date;
    private Status status;
    private String place;
    private List<Presentation> presentations;
    private List<User> users;


    public Conference() {}

    public Conference(String title, String description, LocalDate date, Status status, String place) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.status = status;
        this.place = place;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setPresentations(List<Presentation> presentations) {
        this.presentations = presentations;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public Status getStatus() {
        return status;
    }

    public String getPlace() {
        return place;
    }

    public List<Presentation> getPresentations() {
        return presentations;
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conference that = (Conference) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}