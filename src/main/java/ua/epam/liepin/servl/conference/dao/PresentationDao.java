package ua.epam.liepin.servl.conference.dao;

import ua.epam.liepin.servl.conference.entity.Presentation;
import java.util.List;

public interface PresentationDao extends GenericDao<Presentation> {
    void updatePresentation(int id, String title, String description, int conferenceId, int speakerId, boolean status);

    List<Presentation> findAll(int offset, int noOfRecords, String sort, String sortDir);

    List<Presentation> findAll(int offset, int noOfRecords);

    List<Presentation> findBySpeaker(int speakerId);

    int getNoOfRecords();
}
