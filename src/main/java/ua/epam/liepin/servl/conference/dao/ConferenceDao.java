package ua.epam.liepin.servl.conference.dao;

import ua.epam.liepin.servl.conference.entity.Conference;
import ua.epam.liepin.servl.conference.entity.Presentation;
import ua.epam.liepin.servl.conference.entity.Status;
import ua.epam.liepin.servl.conference.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface ConferenceDao extends GenericDao<Conference> {
    List<Presentation> getPresentationsFromConference(int conferenceId);

    void updateConference (int id, String title, String description, LocalDate date, Status status, String place);

    List<User> getUsersFromConference(int conferenceId);

    boolean checkUserPresence(int userId, int conferenceId);

    void insertUser(User user, int conferenceId);

    List<Conference> findByTitle(String text);

    int getUserCount (int conferenceId);

    List<Conference> findAll(int offset, int noOfRecords, String sort, String sortDir);

    int getNoOfRecords();
}