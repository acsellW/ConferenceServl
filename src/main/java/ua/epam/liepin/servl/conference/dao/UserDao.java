package ua.epam.liepin.servl.conference.dao;


import ua.epam.liepin.servl.conference.entity.Conference;
import ua.epam.liepin.servl.conference.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {

    List<User> findSpeakers();

    void changeRoleToSpeaker(int userId);

    void changeRoleToUser(int userId);

    User getUserByEmailPassword(String email, String password);

    int getNoOfRecords();
}
