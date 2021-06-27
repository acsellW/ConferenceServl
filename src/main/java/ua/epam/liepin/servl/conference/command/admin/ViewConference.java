package ua.epam.liepin.servl.conference.command.admin;

import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.entity.Conference;
import ua.epam.liepin.servl.conference.entity.Presentation;
import ua.epam.liepin.servl.conference.entity.User;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.ConferenceService;
import ua.epam.liepin.servl.conference.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewConference implements Command {
    private final ConferenceService conferenceService;
    private final UserService userService;

    public ViewConference() {
        conferenceService = ServiceFactory.getInstance().getConferenceService();
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public String execute(HttpServletRequest request) {

        int conferenceId = Integer.parseInt(request.getParameter("conferenceId"));
        Conference conference = conferenceService.findById(conferenceId);
        request.setAttribute("conference", conference);
        List<User> speakers = userService.findSpeakers();
        request.setAttribute("speakers", speakers);

        List<Presentation> presentations = conferenceService.getPresentationsFromConference(conferenceId);
        request.setAttribute("presentations", presentations);

        return "/admin/view_conference.jsp";
    }
}