package ua.epam.liepin.servl.conference.service;

import ua.epam.liepin.servl.conference.entity.Conference;
import ua.epam.liepin.servl.conference.entity.User;

import java.util.List;

public interface UserService {
    void createUser(String email, String password, String firstName, String lastName);

    void delete(int userId);

    User findById(int userId);

    List<User> findSpeakers();

    void changeRoleToSpeaker(int userId);

    void changeRoleToUser(int userId);

    User getUserByEmailPassword(String email, String password);

    List<User> findAll(int offset, int noOfRecords);

    int getNoOfRecords();
}
