package ua.epam.liepin.servl.conference.command.admin;

import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.constant.Constants;
import ua.epam.liepin.servl.conference.entity.User;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewAllUsers implements Command {
    private final UserService userService;

    public ViewAllUsers() {
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        int page = Constants.ONE;
        int recordsPerPage = Constants.SIX;

        if (request.getParameter(Constants.PAGE) != null) {
            page = Integer.parseInt(request.getParameter(Constants.PAGE));
        }
        List<User> users = userService.findAll((page - Constants.ONE) * recordsPerPage, recordsPerPage);
        int noOfRecords = userService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * Constants.ONE_DOUBLE / recordsPerPage);

        request.setAttribute("users", users);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        return "/admin/view_users.jsp";
    }
}
