package ua.epam.liepin.servl.conference.dto;

import ua.epam.liepin.servl.conference.entity.Conference;
import ua.epam.liepin.servl.conference.entity.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class UserConferenceDTO {
    private int id;
    private String title;
    private String description;
    private LocalDate date;
    private Status status;
    private String place;
    private boolean userPresent;

    public UserConferenceDTO() {}

    public UserConferenceDTO(Conference conference, boolean userPresent) {
        this.id = conference.getId();
        this.title = conference.getTitle();
        this.description = conference.getDescription();
        this.date = conference.getDate();
        this.status = conference.getStatus();
        this.place = conference.getPlace();
        this.userPresent = userPresent;
    }

    public boolean isUserPresent() {
        return userPresent;
    }

    public void setUserPresent(boolean userPresent) {
        this.userPresent = userPresent;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserConferenceDTO that = (UserConferenceDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}