package ua.epam.liepin.servl.conference.command.speaker;

import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.entity.Conference;
import ua.epam.liepin.servl.conference.entity.Presentation;
import ua.epam.liepin.servl.conference.entity.User;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.ConferenceService;
import ua.epam.liepin.servl.conference.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewConferenceCommand implements Command {
    private final ConferenceService conferenceService;
    private final UserService userService;

    public ViewConferenceCommand() {
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

        int userCount = conferenceService.getUserCount(conferenceId);
        request.setAttribute("userCount", userCount);

        List<Presentation> presentations = conferenceService.getPresentationsFromConference(conferenceId);
        request.setAttribute("presentations", presentations);

        return "/speaker/view_conference.jsp";
    }
}
