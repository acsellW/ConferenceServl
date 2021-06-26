package ua.epam.liepin.servl.conference.command.user;

import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.entity.Conference;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindConferences implements Command {

    private final ConferenceService conferenceService;

    public FindConferences() {
        conferenceService = ServiceFactory.getInstance().getConferenceService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String text = request.getParameter("text");
        List<Conference> conferenceList = conferenceService.findByTitle(text);
        request.setAttribute("conferenceList", conferenceList);

        return "/user/search_result.jsp";
    }
}
