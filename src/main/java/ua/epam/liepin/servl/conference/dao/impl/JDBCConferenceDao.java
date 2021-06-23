package ua.epam.liepin.servl.conference.dao.impl;

import ua.epam.liepin.servl.conference.dao.ConferenceDao;
import ua.epam.liepin.servl.conference.entity.Conference;
import ua.epam.liepin.servl.conference.entity.Presentation;
import ua.epam.liepin.servl.conference.entity.Status;
import ua.epam.liepin.servl.conference.entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JDBCConferenceDao implements ConferenceDao {
    private final Connection connection;
    private final JDBCUserDao jdbcUserDao;
    private final JDBCPresentationDao jdbcPresentationDao;
    private int noOfRecords;

    public JDBCConferenceDao(Connection connection) {
        this.connection = connection;
        jdbcUserDao = new JDBCUserDao(this.connection);
        jdbcPresentationDao = new JDBCPresentationDao(this.connection);
    }

    @Override
    public void create(Conference entity) {

        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO conference (id, title, description, creator_id, date, status, place) VALUES (?,?,?,?,?)")) {
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getTitle());
            ps.setString(3, entity.getDescription());
            ps.setInt(4, entity.getCreator());
            ps.setDate(5, Date.valueOf(entity.getDate()));
            ps.setString(6, entity.getStatus().toString());
            ps.setString(7, entity.getPlace());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Conference findById(int id) {
        Conference conference = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM conference WHERE id = ?")) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                conference = extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conference;
    }

    @Override
    public List<Conference> findAll() {
        List<Conference> conferences = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM conference")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                conferences.add(extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conferences;
    }


    @Override
    public void delete(int id) {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE presentation SET conference_id = null WHERE conference_id = ?");
             PreparedStatement ps1 = connection.prepareStatement("DELETE FROM conference WHERE id = ?")) {

            ps.setInt(1, id);
            ps1.setInt(1,id);

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            ps.execute();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertUsers(List<User> users, int conferenceId) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO user_has_conference (user_id, conference_id) VALUES (?,?)")) {
            for (User user : users) {
                int userId = user.getId();
                ps.setInt(1, userId);
                ps.setInt(2, conferenceId);
                ps.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Presentation> getPresentationsFromConference(int conferenceId) {
        List<Presentation> presentations = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT id FROM presentation WHERE conference_id = ?")) {
            ps.setInt(1, conferenceId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                Presentation presentation = jdbcPresentationDao.findById(userId);
                presentations.add(presentation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return presentations;
    }

    public List<User> getUsersFromConference(int orderId) {
        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT user_id FROM user_has_conference WHERE conference_id = ?")) {
            ps.setInt(1, orderId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                User user = jdbcUserDao.findById(userId);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public int getLastId() {
        int lastId = 0;
        try (PreparedStatement ps = connection.prepareStatement("SELECT coalesce(max(id),0) FROM conference;")) {
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next())
                lastId = resultSet.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lastId;
    }

    private Conference extractFromResultSet(ResultSet resultSet) throws SQLException {
        Conference conference = new Conference();
        conference.setId(resultSet.getInt("id"));
        conference.setTitle(resultSet.getString("title"));
        conference.setDescription(resultSet.getString("description"));
        conference.setCreator(resultSet.getInt("creator_id"));
        conference.setDate(LocalDate.parse(resultSet.getString("date")));
        conference.setStatus(Status.valueOf(resultSet.getString("status")));
        conference.setPlace(resultSet.getString("place"));
        conference.setUsers(getUsersFromConference(conference.getId()));
        conference.setPresentations(getPresentationsFromConference(conference.getId()));
        return conference;
    }

    public List<Conference> findAll(int offset, int noOfRecords) {
        List<Conference> conferences = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT SQL_CALC_FOUND_ROWS * FROM conference LIMIT ?, ?")) {
            ps.setInt(1, offset);
            ps.setInt(2, noOfRecords);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Conference conference = new Conference();
                conference.setId(resultSet.getInt("id"));
                conference.setTitle(resultSet.getString("title"));
                conference.setDescription(resultSet.getString("description"));
                conference.setCreator(resultSet.getInt("creator_id"));
                conference.setDate(LocalDate.parse(resultSet.getString("date")));
                conference.setStatus(Status.valueOf(resultSet.getString("status")));
                conference.setPlace(resultSet.getString("place"));

                conferences.add(conference);
            }

            resultSet = ps.executeQuery("SELECT FOUND_ROWS()");
            if (resultSet.next()) {
                this.noOfRecords = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conferences;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }
}
