package ua.epam.liepin.servl.conference.command;

import javax.servlet.http.HttpServletRequest;

public class ErrorCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/404.jsp";
    }
}