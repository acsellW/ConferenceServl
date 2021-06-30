package ua.epam.liepin.servl.conference.command;

import ua.epam.liepin.servl.conference.constant.Constants;
import ua.epam.liepin.servl.conference.entity.Role;
import ua.epam.liepin.servl.conference.entity.User;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class Login implements Command {
    private final UserService userService;

    public Login() {
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String path = "";

        String email = request.getParameter(Constants.EMAIL);
        String password = request.getParameter(Constants.PASSWORD);
        User user = userService.getUserByEmailPassword(email, password);

        if (Objects.isNull(user)) {
            path = "/401.jsp";
        } else if (user.getRole().equals(Role.ROLE_USER)) {
            request.getSession().setAttribute(Constants.USER, user);
            request.getSession().setAttribute(Constants.ROLE, user.getRole());
            path = "user/cabinet";
        } else if (user.getRole().equals(Role.ROLE_SPEAKER)) {
            request.getSession().setAttribute(Constants.USER, user);
            request.getSession().setAttribute(Constants.ROLE, user.getRole());
            path = "speaker/cabinet";
        } else if (user.getRole().equals(Role.ROLE_ADMIN)) {
            request.getSession().setAttribute(Constants.USER, user);
            request.getSession().setAttribute(Constants.ROLE, user.getRole());
            path = "admin/cabinet";
        }
        return path;
    }

}