package ua.epam.liepin.servl.conference.command.speaker;

import ua.epam.liepin.servl.conference.command.Command;

import javax.servlet.http.HttpServletRequest;

public class SpeakerCabinetCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/speaker/cabinet.jsp";
    }
}
