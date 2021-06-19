package ua.epam.liepin.servl.conference.command.admin;

import ua.epam.liepin.servl.conference.command.Command;

import javax.servlet.http.HttpServletRequest;


public class AdminCabinetCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return "/admin/cabinet.jsp";
    }
}
