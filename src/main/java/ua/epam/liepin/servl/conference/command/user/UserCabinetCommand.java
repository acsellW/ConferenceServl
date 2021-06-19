package ua.epam.liepin.servl.conference.command.user;

import ua.epam.liepin.servl.conference.command.Command;

import javax.servlet.http.HttpServletRequest;

public class UserCabinetCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/user/cabinet.jsp";
    }
}
