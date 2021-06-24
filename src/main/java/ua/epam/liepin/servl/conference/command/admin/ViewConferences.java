package ua.epam.liepin.servl.conference.command.admin;

import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.constant.Constants;
import ua.epam.liepin.servl.conference.entity.Conference;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewConferences implements Command {
    private final ConferenceService conferenceService;

    public ViewConferences() {
        conferenceService = ServiceFactory.getInstance().getConferenceService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        int page = Constants.ONE;
        int recordsPerPage = Constants.SIX;

        if (request.getParameter(Constants.PAGE) != null) {
            page = Integer.parseInt(request.getParameter(Constants.PAGE));
        }
        List<Conference> books = conferenceService.findAll((page - Constants.ONE) * recordsPerPage, recordsPerPage);
        int noOfRecords = conferenceService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * Constants.ONE_DOUBLE / recordsPerPage);

        request.setAttribute("conferences", books);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        return "/admin/view_conferences.jsp";
    }
}
