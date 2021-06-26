package ua.epam.liepin.servl.conference.command.admin;


import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.entity.Presentation;
import ua.epam.liepin.servl.conference.entity.Status;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.ConferenceService;
import ua.epam.liepin.servl.conference.service.PresentationService;
import ua.epam.liepin.servl.conference.util.StringValidator;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ChangeSpeaker implements Command {

    private final PresentationService presentationService;
    private final ConferenceService conferenceService;

    public ChangeSpeaker() {
        presentationService = ServiceFactory.getInstance().getPresentationService();
        conferenceService = ServiceFactory.getInstance().getConferenceService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String path = "/401.jsp";
        int speakerId = Integer.parseInt(request.getParameter("speakerId"));
        int presentationId = Integer.parseInt(request.getParameter("presentationId"));
        int conferenceId = Integer.parseInt(request.getParameter("conferenceId"));
        presentationService.addSpeaker(presentationId, speakerId);
        List<Presentation> presentations = conferenceService.getPresentationsFromConference(conferenceId);
        request.setAttribute("presentations", presentations);
        path = "/admin/view_conference";
        return path;
    }

}
