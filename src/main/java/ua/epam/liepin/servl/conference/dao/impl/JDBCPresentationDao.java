package ua.epam.liepin.servl.conference.dao.impl;

import org.apache.log4j.Logger;
import ua.epam.liepin.servl.conference.constant.Constants;
import ua.epam.liepin.servl.conference.dao.PresentationDao;
import ua.epam.liepin.servl.conference.entity.Presentation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCPresentationDao implements PresentationDao {
    private final Connection connection;
    private int noOfRecords;

    private final static Logger LOGGER = Logger.getLogger(JDBCPresentationDao.class);

    public JDBCPresentationDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Presentation entity) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO presentation (title, description, conference_id, speaker_id, status) VALUES (?,?,?,?,?)")) {

            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getDescription());
            ps.setInt(3, entity.getConferenceId());
            ps.setInt(4, entity.getSpeakerId());
            ps.setBoolean(5, entity.isStatus());

            ps.execute();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Presentation findById(int id) {
        Presentation pres = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM presentation WHERE id = ?")) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                pres = mapPresentation(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return pres;
    }

    @Override
    public List<Presentation> findAll() {
        List<Presentation> presentations = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM presentation")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                presentations.add(mapPresentation(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return presentations;
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM presentation WHERE id = ?")) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void updatePresentation(int id, String title, String description, int conferenceId, int speakerId, boolean status) {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE presentation SET title = ?, description = ?, conference_id = ?, speaker_id = ?, status = ? where id = ?")) {
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setInt(3, conferenceId);
            ps.setInt(4, speakerId);
            ps.setBoolean(5, status);
            ps.setInt(6, id);
            ps.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

    }

    @Override
    public List<Presentation> findAll(int offset, int noOfRecords, String sort, String sortDir) {
        List<Presentation> presentations = new ArrayList<>();
        String sqlStatement;
        if (sortDir.equals(Constants.ASC))
            sqlStatement = Constants.FIND_PRESENTATION_SORT_TITLE_ASC;
        else
            sqlStatement = Constants.FIND_PRESENTATION_SORT_TITLE_DESC;
        try (PreparedStatement ps = connection.prepareStatement(sqlStatement)) {
            ps.setInt(1, offset);
            ps.setInt(2, noOfRecords);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                presentations.add(mapPresentation(resultSet));
            }

            resultSet = ps.executeQuery("SELECT FOUND_ROWS()");
            if (resultSet.next()) {
                this.noOfRecords = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return presentations;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Presentation> findAll(int offset, int noOfRecords) {
        List<Presentation> presentations = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT SQL_CALC_FOUND_ROWS * FROM presentation limit ?, ?")) {
            ps.setInt(1, offset);
            ps.setInt(2, noOfRecords);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                presentations.add(mapPresentation(resultSet));
            }

            resultSet = ps.executeQuery("SELECT FOUND_ROWS()");
            if (resultSet.next()) {
                this.noOfRecords = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return presentations;
    }

    @Override
    public List<Presentation> findBySpeaker(int speakerId) {
        List<Presentation> presentations = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM presentation WHERE speaker_id = ?")) {
            ps.setInt(1, speakerId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                presentations.add(mapPresentation(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return presentations;
    }

    private Presentation mapPresentation(ResultSet rs) throws SQLException {
        Presentation pres = new Presentation();
        pres.setId(rs.getInt("id"));
        pres.setTitle(rs.getString("title"));
        pres.setDescription(rs.getString("description"));
        pres.setConferenceId(rs.getInt("conference_id"));
        pres.setSpeakerId(rs.getInt("speaker_id"));
        pres.setStatus(rs.getBoolean("status"));
        return pres;
    }
}