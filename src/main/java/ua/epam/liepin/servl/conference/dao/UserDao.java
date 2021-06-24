package ua.epam.liepin.servl.conference.dao;


import ua.epam.liepin.servl.conference.entity.Conference;
import ua.epam.liepin.servl.conference.entity.User;

public interface UserDao extends GenericDao<User> {
    void setConferenceToUser(User user, Conference order);

    User findAdmin();

    void changeRoleToSpeaker(int userId);

    void changeRoleToUser(int userId);

    User getUserByEmailPassword(String email, String password);

    int getNoOfRecords();
}
