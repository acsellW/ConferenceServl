package ua.epam.liepin.servl.conference.command;

import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.UserService;
import ua.epam.liepin.servl.conference.util.StringValidator;

import javax.servlet.http.HttpServletRequest;

public class Registration implements Command {
    private final UserService userService;
    private final StringValidator stringValidator;

    public Registration() {
        userService = ServiceFactory.getInstance().getUserService();
        stringValidator = StringValidator.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String path = "401.jsp";
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (checkParameters(name, surname, email, password)) {
            userService.createUser(email, password, name, surname);
            path = "login.jsp";
        }
        return path;
    }

    private boolean checkParameters(String name, String surname, String email, String password) {
        boolean first = stringValidator.checkName(name);
        boolean last = stringValidator.checkName(surname);
        boolean mail = stringValidator.checkEmail(email);
        boolean pass = stringValidator.checkPassword(password);
        return first && last && mail && pass;
    }
}
