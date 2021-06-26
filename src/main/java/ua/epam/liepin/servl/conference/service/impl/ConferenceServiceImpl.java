package ua.epam.liepin.servl.conference.service.impl;

import ua.epam.liepin.servl.conference.dao.ConferenceDao;
import ua.epam.liepin.servl.conference.dao.impl.JDBCDaoFactory;
import ua.epam.liepin.servl.conference.entity.Conference;
import ua.epam.liepin.servl.conference.entity.Presentation;
import ua.epam.liepin.servl.conference.entity.Status;
import ua.epam.liepin.servl.conference.entity.User;
import ua.epam.liepin.servl.conference.service.ConferenceService;

import java.time.LocalDate;
import java.util.List;

public class ConferenceServiceImpl implements ConferenceService {
    private final ConferenceDao conferenceDao;

    public ConferenceServiceImpl() {
        JDBCDaoFactory jdbcDaoFactory = new JDBCDaoFactory();
        conferenceDao = jdbcDaoFactory.createConferenceDao();
    }


    @Override
    public void create(String title, String description, LocalDate date, Status status, String place) {
        Conference conference = new Conference(title, description, date, status, place);
        conferenceDao.create(conference);
    }

    @Override
    public Conference findById(int id) {
        return conferenceDao.findById(id);
    }

    @Override
    public List<Conference> findAll() {
        return conferenceDao.findAll();
    }

    @Override
    public List<Conference> findAll(int offset, int noOfRecords) {
        return conferenceDao.findAll(offset, noOfRecords);
    }

    @Override
    public List<Conference> findAll(int offset, int noOfRecords, String sort, String sortDir) {
        return conferenceDao.findAll(offset, noOfRecords, sort, sortDir);
    }

    @Override
    public List<Conference> findByTitle(String text) {
        return conferenceDao.findByTitle(text);
    }

    @Override
    public List<User> getUsersFromConference(int conferenceId) {
        return conferenceDao.getUsersFromConference(conferenceId);
    }

    @Override
    public List<Presentation> getPresentationsFromConference(int conferenceId) {
        return conferenceDao.getPresentationsFromConference(conferenceId);
    }

    @Override
    public void insertUsers(List<User> users, int conferenceId) {
        conferenceDao.insertUsers(users, conferenceId);
    }
    @Override
    public void insertUser(User user, int conferenceId) {
        conferenceDao.insertUser(user, conferenceId);
    }

    @Override
    public void insertPresentations(List<Presentation> presentations, int conferenceId) {
        conferenceDao.insertPresentations(presentations, conferenceId);
    }

    @Override
    public void delete(int id) {
        conferenceDao.delete(id);
    }

    @Override
    public void updateConference(int id, String title, String description, LocalDate date, Status status, String place) {
        conferenceDao.updateConference(id, title, description, date, status, place);
    }

    @Override
    public int getNoOfRecords() {
        return conferenceDao.getNoOfRecords();
    }
}
