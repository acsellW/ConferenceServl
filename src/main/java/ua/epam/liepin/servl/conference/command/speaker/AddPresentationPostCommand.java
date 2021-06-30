package ua.epam.liepin.servl.conference.command.speaker;


import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.constant.Constants;
import ua.epam.liepin.servl.conference.entity.Conference;
import ua.epam.liepin.servl.conference.entity.Presentation;
import ua.epam.liepin.servl.conference.entity.Role;
import ua.epam.liepin.servl.conference.entity.User;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.ConferenceService;
import ua.epam.liepin.servl.conference.service.PresentationService;
import ua.epam.liepin.servl.conference.util.StringValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddPresentationPostCommand implements Command {

    private final PresentationService presentationService;
    private final ConferenceService conferenceService;
    private final StringValidator stringValidator;

    public AddPresentationPostCommand() {
        presentationService = ServiceFactory.getInstance().getPresentationService();
        conferenceService = ServiceFactory.getInstance().getConferenceService();
        stringValidator = StringValidator.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        int conferenceId = Integer.parseInt(request.getParameter("conferenceId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        User curentUser = (User) request.getSession().getAttribute(Constants.USER);
        presentationService.create(title, description, conferenceId, curentUser.getId(), false);

        Conference conference = conferenceService.findById(conferenceId);
        request.setAttribute("conference", conference);
        List<Presentation> presentations = conferenceService.getPresentationsFromConference(conferenceId);
        request.setAttribute("presentations", presentations);

        return "/speaker/view_conference";
    }
}