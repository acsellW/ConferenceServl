package ua.epam.liepin.servl.conference.service;

import ua.epam.liepin.servl.conference.entity.Presentation;

import java.time.LocalDate;
import java.util.List;

public interface PresentationService {
    void create(String title, String description, int conferenceId, int speakerId, boolean status);

    Presentation findById(int id);

    List<Presentation> findAll();

    List<Presentation> findAll(int offset, int noOfRecords);

    List<Presentation> findAll(int offset, int noOfRecords, String sort, String sortDir);

    void delete(int id);

    void approvePresentation(int id);

    List<Presentation> findBySpeaker(int speakerId);

    void addSpeaker(int presentationId, int userId);

    void updatePresentation(int id, String title, String description, int conferenceId, int speakerId, boolean status);

    int getNoOfRecords();
}
