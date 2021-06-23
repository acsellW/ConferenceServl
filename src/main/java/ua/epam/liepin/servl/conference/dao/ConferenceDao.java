package ua.epam.liepin.servl.conference.dao;

import ua.epam.liepin.servl.conference.entity.Conference;
import ua.epam.liepin.servl.conference.entity.Presentation;
import ua.epam.liepin.servl.conference.entity.User;

import java.util.List;

public interface ConferenceDao extends GenericDao<Conference> {
    List<Presentation> getPresentationsFromConference(int conferenceId);

    List<User> getUsersFromConference(int conferenceId);
    void insertUsers(List<User> users, int conferenceId);


    int getLastId();
    int getNoOfRecords();
}
