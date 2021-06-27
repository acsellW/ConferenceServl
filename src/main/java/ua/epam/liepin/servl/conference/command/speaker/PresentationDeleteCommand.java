package ua.epam.liepin.servl.conference.command.speaker;


import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.constant.Constants;
import ua.epam.liepin.servl.conference.entity.Conference;
import ua.epam.liepin.servl.conference.entity.Presentation;
import ua.epam.liepin.servl.conference.entity.User;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.ConferenceService;
import ua.epam.liepin.servl.conference.service.PresentationService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PresentationDeleteCommand implements Command {
    private final PresentationService presentationService;
    private final ConferenceService conferenceService;

    public PresentationDeleteCommand() {
        presentationService = ServiceFactory.getInstance().getPresentationService();
        conferenceService = ServiceFactory.getInstance().getConferenceService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        int presentationId = Integer.parseInt(request.getParameter("presentationId"));

        presentationService.delete(presentationId);
        User currentUser = (User) request.getSession().getAttribute(Constants.USER);
        List<Presentation> presentations = presentationService.findBySpeaker(currentUser.getId());
        request.setAttribute("presentations", presentations);

        return "/speaker/view_presentations";
    }
}
