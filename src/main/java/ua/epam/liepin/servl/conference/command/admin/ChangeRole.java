package ua.epam.liepin.servl.conference.command.admin;

import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ChangeRole implements Command {

    private final UserService userService;

    public ChangeRole() {
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        System.out.println(request.getParameter("id"));
        String role = request.getParameter("role");
        System.out.println(request.getParameter("role"));

        if (role.equals("ROLE_USER"))
            userService.changeRoleToSpeaker(Integer.parseInt(id));
        else
            userService.changeRoleToUser(Integer.parseInt(id));

        return "/admin/view_users";
    }
}
