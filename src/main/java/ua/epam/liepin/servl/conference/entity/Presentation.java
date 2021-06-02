package ua.epam.liepin.servl.conference.entity;

import java.util.Objects;

public class Presentation {
    private int id;
    private String title;
    private String description;
    private User speaker;
    private boolean status;
    private String place;

    public Presentation() { }

    public Presentation(String title, String description, User speaker, boolean status, String place) {
        this.title = title;
        this.description = description;
        this.speaker = speaker;
        this.status = status;
        this.place = place;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getSpeaker() {
        return speaker;
    }

    public void setSpeaker(User speaker) {
        this.speaker = speaker;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Presentation that = (Presentation) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
