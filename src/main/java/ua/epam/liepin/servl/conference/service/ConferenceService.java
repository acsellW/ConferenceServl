package ua.epam.liepin.servl.conference.service;

import ua.epam.liepin.servl.conference.entity.Conference;
import ua.epam.liepin.servl.conference.entity.Presentation;
import ua.epam.liepin.servl.conference.entity.Status;
import ua.epam.liepin.servl.conference.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface ConferenceService {
    void create(String title, String description, LocalDate date, Status status, String place);

    Conference findById(int id);

    List<Conference> findAll();

    List<Conference> findAll(int offset, int noOfRecords);

    List<Conference> findAll(int offset, int noOfRecords, String sort, String sortDir);

    List<Conference> findByTitle(String text);

    List<Presentation> getPresentationsFromConference(int conferenceId);

    void insertUser(User user, int conferenceId);

    boolean checkUserPresence(int userId, int conferenceId);

    int getUserCount (int conferenceId);

    void delete(int id);

    void updateConference(int id, String title, String description, LocalDate date, Status status, String place);

    int getNoOfRecords();
}
