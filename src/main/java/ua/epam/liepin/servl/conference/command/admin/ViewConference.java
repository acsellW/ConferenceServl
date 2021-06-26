package ua.epam.liepin.servl.conference.command.admin;

import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.constant.Constants;
import ua.epam.liepin.servl.conference.entity.Conference;
import ua.epam.liepin.servl.conference.entity.Presentation;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.ConferenceService;
import ua.epam.liepin.servl.conference.service.PresentationService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewConference implements Command {
    private final ConferenceService conferenceService;
    private final PresentationService presentationService;

    public ViewConference() {
        conferenceService = ServiceFactory.getInstance().getConferenceService();
        presentationService = ServiceFactory.getInstance().getPresentationService();
    }

    @Override
    public String execute(HttpServletRequest request) {

        int confId = Integer.parseInt(request.getParameter("id"));
        Conference conference = conferenceService.findById(confId);
        request.setAttribute("conference", conference);

        List<Presentation> presentations = conferenceService.getPresentationsFromConference(confId);
        request.setAttribute("presentations", presentations);

        return "/admin/view_conference.jsp";
    }
}
