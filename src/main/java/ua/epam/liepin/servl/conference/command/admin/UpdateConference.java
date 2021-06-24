package ua.epam.liepin.servl.conference.command.admin;

import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.entity.Status;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.ConferenceService;
import ua.epam.liepin.servl.conference.util.StringValidator;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class UpdateConference implements Command {
    private final ConferenceService conferenceService;
    private final StringValidator stringValidator;

    public UpdateConference() {
        conferenceService = ServiceFactory.getInstance().getConferenceService();
        stringValidator = StringValidator.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String idString = request.getParameter("id");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String date = request.getParameter("date");
        String status = request.getParameter("status");
        String place = request.getParameter("place");

        if (stringValidator.checkConferenceParameters(title, date, status)) {
            int id = Integer.parseInt(idString);
            conferenceService.updateConference(id, title, description, LocalDate.parse(date), Status.valueOf(status), place);
        }
        return "/admin/view_conferences";
    }
}
