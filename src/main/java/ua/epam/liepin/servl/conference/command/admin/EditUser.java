package ua.epam.liepin.servl.conference.command.admin;

import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.entity.User;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.UserService;;

import javax.servlet.http.HttpServletRequest;

public class EditUser implements Command {
    private final UserService userService;

    public EditUser() {
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String idString = request.getParameter("id");
        User userToEdit = userService.findById(Integer.parseInt(idString));

        request.setAttribute("user", userToEdit);

        return "/admin/edit_user.jsp";
    }
}
