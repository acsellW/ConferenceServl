package ua.epam.liepin.servl.conference.dao.impl;

import ua.epam.liepin.servl.conference.dao.ConferenceDao;
import ua.epam.liepin.servl.conference.dao.DaoFactory;
import ua.epam.liepin.servl.conference.dao.PresentationDao;
import ua.epam.liepin.servl.conference.dao.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private final DataSource dataSource = JDBCConnection.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public ConferenceDao createConferenceDao() {
        return new JDBCConferenceDao(getConnection());
    }

    @Override
    public PresentationDao createPresentationDao() {
        return new JDBCPresentationDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
