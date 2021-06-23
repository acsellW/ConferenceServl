package ua.epam.liepin.servl.conference.constant;

public class Constants {
    public static final String USER = "user";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";
    public static final String LOCALE = "locale";

    public static final String ASC = "asc";
    public static final String DESC = "desc";

    public static final String FIND_PRESENTATION_SORT_TITLE_ASC = "SELECT SQL_CALC_FOUND_ROWS * FROM presentation ORDER BY title ASC LIMIT ?, ?";
    public static final String FIND_PRESENTATION_SORT_TITLE_DESC = "SELECT SQL_CALC_FOUND_ROWS * FROM presentation ORDER BY title DESC LIMIT ?, ?";

    public static final String FIND_PRESENTATION = "SELECT SQL_CALC_FOUND_ROWS * FROM presentation ORDER BY id ASC LIMIT ?, ?";
}
