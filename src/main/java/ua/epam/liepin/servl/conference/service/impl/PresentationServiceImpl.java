package ua.epam.liepin.servl.conference.service.impl;

import ua.epam.liepin.servl.conference.dao.PresentationDao;
import ua.epam.liepin.servl.conference.dao.impl.JDBCDaoFactory;
import ua.epam.liepin.servl.conference.entity.Presentation;
import ua.epam.liepin.servl.conference.service.PresentationService;

import java.util.List;

public class PresentationServiceImpl implements PresentationService {
    private final PresentationDao presentationDao;

    public PresentationServiceImpl() {
        JDBCDaoFactory jdbcDaoFactory = new JDBCDaoFactory();
        presentationDao = jdbcDaoFactory.createPresentationDao();
    }

    @Override
    public void create(String title, String description, int conferenceId, int speakerId, boolean status) {
        Presentation presentation = new Presentation(title, description, conferenceId, speakerId, status);
        presentationDao.create(presentation);
    }

    @Override
    public Presentation findById(int id) {
        return presentationDao.findById(id);
    }

    @Override
    public List<Presentation> findAll() {
        return presentationDao.findAll();
    }

    @Override
    public List<Presentation> findAll(int offset, int noOfRecords, String sort, String sortDir) {
        return presentationDao.findAll(offset, noOfRecords, sort, sortDir);
    }

    @Override
    public List<Presentation> findAll(int offset, int noOfRecords) {
        return presentationDao.findAll(offset, noOfRecords);
    }

    @Override
    public void delete(int id) {
        presentationDao.delete(id);
    }

    @Override
    public void approvePresentation(int id) {
        Presentation pres = presentationDao.findById(id);
        presentationDao.updatePresentation(id,
                pres.getTitle(),
                pres.getDescription(),
                pres.getConferenceId(),
                pres.getSpeakerId(),
                true);
    }

    @Override
    public List<Presentation> findBySpeaker(int speakerId) {
        return presentationDao.findBySpeaker(speakerId);
    }

    @Override
    public void addSpeaker(int presentationId, int userId) {
        Presentation pres = presentationDao.findById(presentationId);
        presentationDao.updatePresentation(
                presentationId,
                pres.getTitle(),
                pres.getDescription(),
                pres.getConferenceId(),
                userId,
                pres.isStatus());
    }

    @Override
    public void updatePresentation(int id, String title, String description, int conferenceId, int speakerId, boolean status) {
        presentationDao.updatePresentation(id, title, description, conferenceId, speakerId, status);
    }


    @Override
    public int getNoOfRecords() {
        return presentationDao.getNoOfRecords();
    }
}
