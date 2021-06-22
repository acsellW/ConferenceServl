package ua.epam.liepin.servl.conference.command;

import javax.servlet.http.HttpServletRequest;

public class AccessDeniedCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/403.jsp";
    }
}
