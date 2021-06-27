package ua.epam.liepin.servl.conference.command.admin;


import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.entity.Presentation;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.ConferenceService;
import ua.epam.liepin.servl.conference.service.PresentationService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ApprovePresentation implements Command {

    private final PresentationService presentationService;
    private final ConferenceService conferenceService;

    public ApprovePresentation() {
        presentationService = ServiceFactory.getInstance().getPresentationService();
        conferenceService = ServiceFactory.getInstance().getConferenceService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        int presentationId = Integer.parseInt(request.getParameter("presentationId"));
        int conferenceId = Integer.parseInt(request.getParameter("conferenceId"));
        presentationService.approvePresentation(presentationId);
        List<Presentation> presentations = conferenceService.getPresentationsFromConference(conferenceId);
        request.setAttribute("presentations", presentations);
        return "/admin/view_conference";
    }

}
