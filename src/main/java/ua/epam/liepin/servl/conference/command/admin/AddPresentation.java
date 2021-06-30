package ua.epam.liepin.servl.conference.command.admin;

import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.entity.User;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddPresentation implements Command {
    private final UserService userService;

    public AddPresentation() {
        userService = ServiceFactory.getInstance().getUserService();
    }
    @Override
    public String execute(HttpServletRequest request) {
        List<User> speakers = userService.findSpeakers();
        request.setAttribute("speakers", speakers);
        request.setAttribute("id", request.getParameter("conferenceId"));
        return "/admin/add_presentation.jsp";
    }
}