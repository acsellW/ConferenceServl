package ua.epam.liepin.servl.conference.service.impl;

import ua.epam.liepin.servl.conference.dao.UserDao;
import ua.epam.liepin.servl.conference.dao.impl.JDBCDaoFactory;
import ua.epam.liepin.servl.conference.entity.Conference;
import ua.epam.liepin.servl.conference.entity.Role;
import ua.epam.liepin.servl.conference.entity.User;
import ua.epam.liepin.servl.conference.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl() {
        JDBCDaoFactory jdbcDaoFactory = new JDBCDaoFactory();
        userDao = jdbcDaoFactory.createUserDao();
    }

    @Override
    public void createUser(String email, String password, String name, String surname) {
        User user = new User(email, password, Role.ROLE_USER, name, surname);
        userDao.create(user);
    }

    @Override
    public void delete(int userId) {
        userDao.delete(userId);
    }

    @Override
    public User findById(int userId) {
        return userDao.findById(userId);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User findAdmin() {
        return userDao.findAdmin();
    }

    @Override
    public void setConferenceToUser(User user, Conference conference) {
        userDao.setConferenceToUser(user, conference);
    }

    @Override
    public List<User> findSpeakers() {
        return userDao.findSpeakers();
    }

    @Override
    public void changeRoleToSpeaker(int userId) {
        userDao.changeRoleToSpeaker(userId);
    }

    @Override
    public void changeRoleToUser(int userId) {
        userDao.changeRoleToUser(userId);
    }

    @Override
    public User getUserByEmailPassword(String email, String password) {
        return userDao.getUserByEmailPassword(email, password);
    }

    @Override
    public List<User> findAll(int offset, int noOfRecords) {
        return userDao.findAll(offset, noOfRecords);
    }

    @Override
    public int getNoOfRecords() {
        return userDao.getNoOfRecords();
    }
}
