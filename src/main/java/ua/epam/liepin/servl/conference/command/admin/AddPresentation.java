package ua.epam.liepin.servl.conference.command.admin;

import ua.epam.liepin.servl.conference.command.Command;

import javax.servlet.http.HttpServletRequest;

public class AddPresentation implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("id", request.getParameter("id"));
        return "/admin/add_presentation.jsp";
    }
}
