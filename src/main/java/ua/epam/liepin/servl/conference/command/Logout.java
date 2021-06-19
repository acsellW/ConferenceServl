package ua.epam.liepin.servl.conference.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Logout implements Command {

    public static final String LOCALE = "locale";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(LOCALE);
        session.invalidate();
        request.getSession().setAttribute(LOCALE, locale);
        return "logout.jsp";
    }

}
