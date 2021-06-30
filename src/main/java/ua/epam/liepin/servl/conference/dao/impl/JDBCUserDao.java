package ua.epam.liepin.servl.conference.dao.impl;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import ua.epam.liepin.servl.conference.dao.UserDao;
import ua.epam.liepin.servl.conference.entity.Conference;
import ua.epam.liepin.servl.conference.entity.Role;
import ua.epam.liepin.servl.conference.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDao implements UserDao {
    private final Connection connection;
    private int noOfRecords;

    private final static Logger LOGGER = Logger.getLogger(JDBCUserDao.class);

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO user (email, password, role, name, surname) VALUES (?,?,?,?,?)")) {

            ps.setString(1, entity.getEmail());
            ps.setString(2, BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt()));
            ps.setString(3, entity.getRole().toString());
            ps.setString(4, entity.getName());
            ps.setString(5, entity.getSurname());

            ps.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public User findById(int id) {
        User user = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE id = ?")) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                user = extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE role != 'ROLE_ADMIN'")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                User user = extractFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return users;
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM user WHERE id = ?");
             PreparedStatement ps1 = connection.prepareStatement("UPDATE presentation SET speaker_id = null WHERE speaker_id = ?")) {
            ps.setInt(1, id);
            ps1.setInt(1, id);

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            ps.execute();
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<User> findAll(int offset, int noOfRecords) {
        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "select SQL_CALC_FOUND_ROWS * from user WHERE role != 'ROLE_ADMIN' limit ?,?")) {
            ps.setInt(1, offset);
            ps.setInt(2, noOfRecords);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                users.add(extractFromResultSet(resultSet));
            }
            resultSet = ps.executeQuery("SELECT FOUND_ROWS()");
            if (resultSet.next()) {
                this.noOfRecords = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return users;
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
    public List<User> findSpeakers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE role = 'ROLE_SPEAKER'")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                User user = extractFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return users;
    }


    @Override
    public void changeRoleToSpeaker(int userId) {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE user SET role = ? WHERE id = ?")) {
            ps.setString(1, Role.ROLE_SPEAKER.toString());
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void changeRoleToUser(int userId) {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE user SET role = ? WHERE id = ?")) {
            ps.setString(1, Role.ROLE_USER.toString());
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public User getUserByEmailPassword(String email, String password) {
        User user = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE email = ?")) {
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                user = extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        if (user != null) {
            if (!BCrypt.checkpw(password, user.getPassword())) user = null;
        }
        return user;
    }

    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    private User extractFromResultSet(ResultSet resultSet) throws SQLException {

        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(Role.valueOf(resultSet.getString("role")));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));

        return user;
    }
}