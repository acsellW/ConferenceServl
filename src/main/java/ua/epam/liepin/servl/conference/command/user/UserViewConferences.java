package ua.epam.liepin.servl.conference.command.user;


import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.constant.Constants;
import ua.epam.liepin.servl.conference.entity.Conference;
import ua.epam.liepin.servl.conference.entity.User;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserViewConferences implements Command {

    private final ConferenceService conferenceService;

    public UserViewConferences() {
        conferenceService = ServiceFactory.getInstance().getConferenceService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        int page = Constants.ONE;
        int recordsPerPage = Constants.SIX;
        String sort = Constants.ID;
        String sortDir = Constants.DESC;

        if (request.getParameter(Constants.PAGE) != null) {
            page = Integer.parseInt(request.getParameter(Constants.PAGE));
        }
        if (request.getParameter(Constants.SORT) != null) {
            sort = request.getParameter(Constants.SORT);
        }
        if (request.getParameter(Constants.SORT_DIR) != null) {
            sortDir = request.getParameter(Constants.SORT_DIR);
        }

        List<Conference> conferences = conferenceService.findAll((page - Constants.ONE) * recordsPerPage, recordsPerPage, sort, sortDir);
        int noOfRecords = conferenceService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * Constants.ONE_DOUBLE / recordsPerPage);

        request.setAttribute("conferences", conferences);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        request.setAttribute("sort", sort);
        request.setAttribute("sortDir", sortDir);

        return "/user/view_conferences.jsp";
    }
}