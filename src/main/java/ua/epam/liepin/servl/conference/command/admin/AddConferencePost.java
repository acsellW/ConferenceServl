package ua.epam.liepin.servl.conference.command.admin;


import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.entity.Status;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.ConferenceService;
import ua.epam.liepin.servl.conference.util.StringValidator;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddConferencePost implements Command {

    private final ConferenceService conferenceService;
    private final StringValidator stringValidator;

    public AddConferencePost() {
        conferenceService = ServiceFactory.getInstance().getConferenceService();
        stringValidator = StringValidator.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String path = "/401.jsp";
        String idString = request.getParameter("id");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String date = request.getParameter("date");
        String status = request.getParameter("status");
        String place = request.getParameter("place");
        if (stringValidator.checkConferenceParameters(title, date, status)) {

            LocalDate publishDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            conferenceService.create(title, description, publishDate, Status.valueOf(status), place);
            path = "/admin/view_conferences";
        }
        return path;
    }

}
