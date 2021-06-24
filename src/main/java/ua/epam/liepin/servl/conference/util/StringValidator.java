package ua.epam.liepin.servl.conference.util;

import org.apache.commons.lang3.EnumUtils;
import ua.epam.liepin.servl.conference.entity.Status;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator {
    private static final StringValidator stringValidator = new StringValidator();

    private StringValidator() {
    }

    public static StringValidator getInstance() {
        return stringValidator;
    }

    public static final String REG_EXP_NAME = "^[A-Za-zА-ЩЬЮЯҐІЇЄа-щьюяґіїє' ]{1,20}";

    public static final String REG_EXP_PASSWORD = "^[a-zA-Z0-9_.$!]{2,30}";

    public static final String REG_EXP_CONFERENCE_TITLE = "^[A-Za-z0-9А-ЩЬЮЯҐІЇЄа-щьюяґіїє' ]{1,40}";

    public static final String REG_EXP_EMAIL = "(\\w+)@(\\w+\\.)(\\w+)";

    public boolean checkEmail(String emailToCheck) {
        Pattern pattern = Pattern.compile(REG_EXP_EMAIL);
        Matcher matcher = pattern.matcher(emailToCheck);
        return matcher.matches();
    }

    public boolean checkName(String nameToCheck) {
        Pattern pattern = Pattern.compile(REG_EXP_NAME);
        Matcher matcher = pattern.matcher(nameToCheck);
        return matcher.matches();
    }

    public boolean checkPassword(String passwordToCheck) {
        Pattern pattern = Pattern.compile(REG_EXP_PASSWORD);
        Matcher matcher = pattern.matcher(passwordToCheck);
        return matcher.matches();
    }

    public boolean checkConferenceTitle(String conferenceTitle) {
        Pattern pattern = Pattern.compile(REG_EXP_CONFERENCE_TITLE);
        Matcher matcher = pattern.matcher(conferenceTitle);
        return matcher.matches();
    }

    private boolean checkStatus(String status) {
        return EnumUtils.isValidEnum(Status.class, status);
    }

    public boolean checkDate(String localDate) {
        return Objects.nonNull(localDate);
    }


    public boolean checkConferenceParameters(String title, String updatedDate, String status) {
        boolean name = checkConferenceTitle(title);
        boolean date = checkDate(updatedDate);
        boolean stat = checkStatus(status);
        return name && date && stat;
    }
}