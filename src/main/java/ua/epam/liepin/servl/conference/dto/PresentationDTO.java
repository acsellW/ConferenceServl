package ua.epam.liepin.servl.conference.dto;

import ua.epam.liepin.servl.conference.entity.Presentation;

import java.util.Objects;

public class PresentationDTO {
    private int id;
    private String title;
    private String description;
    private String speakerName;
    boolean status;

    public PresentationDTO() { }

    public PresentationDTO(Presentation pres, String fullName) {
        this.id = pres.getId();
        this.title = pres.getTitle();
        this.description = pres.getDescription();
        this.speakerName = fullName;
        this.status = pres.isStatus();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PresentationDTO that = (PresentationDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
