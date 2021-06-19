package ua.epam.liepin.servl.conference.dao.impl;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class JDBCConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/conference?useUnicode=yes&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "44Ebotus2626";
    private static DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (JDBCConnection.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl(URL);
                    ds.setUsername(USER);
                    ds.setPassword(PASSWORD);
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }

}
