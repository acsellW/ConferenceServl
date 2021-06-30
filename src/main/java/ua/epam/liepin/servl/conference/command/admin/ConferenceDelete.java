package ua.epam.liepin.servl.conference.command.admin;


import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;

public class ConferenceDelete implements Command {
    private final ConferenceService conferenceService;

    public ConferenceDelete() {
        conferenceService = ServiceFactory.getInstance().getConferenceService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String idString = request.getParameter("conferenceId");

        int id = Integer.parseInt(idString);
        conferenceService.delete(id);


        return "/admin/view_conferences";
    }
}