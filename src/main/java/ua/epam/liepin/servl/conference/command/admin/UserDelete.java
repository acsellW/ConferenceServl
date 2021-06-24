package ua.epam.liepin.servl.conference.command.admin;


import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.entity.User;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserDelete implements Command {
    private final UserService userService;

    public UserDelete() {
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);
        userService.delete(id);

        return "/admin/view_users";
    }
}
