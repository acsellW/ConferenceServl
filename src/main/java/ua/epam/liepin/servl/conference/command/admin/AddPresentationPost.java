package ua.epam.liepin.servl.conference.command.admin;


import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.entity.Conference;
import ua.epam.liepin.servl.conference.entity.Presentation;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.ConferenceService;
import ua.epam.liepin.servl.conference.service.PresentationService;
import ua.epam.liepin.servl.conference.util.StringValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

public class AddPresentationPost implements Command {

    private final PresentationService presentationService;
    private final ConferenceService conferenceService;
    private final StringValidator stringValidator;

    public AddPresentationPost() {
        presentationService = ServiceFactory.getInstance().getPresentationService();
        conferenceService = ServiceFactory.getInstance().getConferenceService();
        stringValidator = StringValidator.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {

        String path = "/401.jsp";
        int conferenceId = Integer.parseInt(request.getParameter("conferenceId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        int speakerId = Integer.parseInt(request.getParameter("speaker"));
        presentationService.create(title, description, conferenceId, speakerId, true);

        Conference conference = conferenceService.findById(conferenceId);
        request.setAttribute("conference", conference);
        List<Presentation> presentations = conferenceService.getPresentationsFromConference(conferenceId);
        request.setAttribute("presentations", presentations);

        path = "/admin/view_conference.jsp";
        return path;
    }

}
