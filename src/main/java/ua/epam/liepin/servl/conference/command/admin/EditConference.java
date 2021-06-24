package ua.epam.liepin.servl.conference.command.admin;

import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.entity.Conference;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.ConferenceService;
import javax.servlet.http.HttpServletRequest;

public class EditConference implements Command {
    private final ConferenceService conferenceService;

    public EditConference() {
        conferenceService = ServiceFactory.getInstance().getConferenceService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String idString = request.getParameter("id");

        Conference conferenceToEdit = conferenceService.findById(Integer.parseInt(idString));
        request.setAttribute("conference", conferenceToEdit);

        return "/admin/edit_conference.jsp";
    }
}
