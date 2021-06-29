package ua.epam.liepin.servl.conference.command.user;


import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.constant.Constants;
import ua.epam.liepin.servl.conference.entity.User;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.ConferenceService;
import ua.epam.liepin.servl.conference.service.PresentationService;
import ua.epam.liepin.servl.conference.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class TakePlace implements Command {
    private final ConferenceService conferenceService;
    private final UserService userService;

    public TakePlace() {
        conferenceService = ServiceFactory.getInstance().getConferenceService();
        userService = ServiceFactory.getInstance().getUserService();

    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER);
        String conferenceId = request.getParameter("conferenceId");
        System.out.println(conferenceId);
        user = userService.findById(user.getId());
        conferenceService.insertUser(user, Integer.parseInt(conferenceId));
        return "/user/cabinet.jsp";
    }
}
