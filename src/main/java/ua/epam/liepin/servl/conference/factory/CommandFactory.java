package ua.epam.liepin.servl.conference.factory;

import ua.epam.liepin.servl.conference.command.*;
import ua.epam.liepin.servl.conference.command.admin.*;
import ua.epam.liepin.servl.conference.command.user.*;
import ua.epam.liepin.servl.conference.command.speaker.*;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CommandFactory {
    private static final CommandFactory commandFactory = new CommandFactory();
    private static final Map<String, Command> commandMap = new HashMap<>();

    private CommandFactory() {
    }

    static {
        commandMap.put("/login", new Login());
        commandMap.put("/logout", new Logout());
        commandMap.put("/registration", new Registration());
        commandMap.put("/404", new ErrorCommand());
        commandMap.put("/403", new AccessDeniedCommand());

        commandMap.put("/admin/cabinet", new AdminCabinetCommand());
        commandMap.put("/admin/view_users", new ViewAllUsers());
        commandMap.put("/admin/change_role", new ChangeRole());
        commandMap.put("/admin/edit_user", new EditUser());
        commandMap.put("/admin/delete_user", new UserDelete());
        commandMap.put("/admin/view_conferences", new ViewConferences());
        commandMap.put("/admin/edit_conference", new EditConference());
        commandMap.put("/admin/update_conference", new UpdateConference());
        commandMap.put("/admin/delete_conference", new ConferenceDelete());
        commandMap.put("/admin/add_conference", new AddConference());
        commandMap.put("/admin/add_conference_post", new AddConferencePost());

        commandMap.put("/speaker/cabinet", new SpeakerCabinetCommand());
        commandMap.put("/speaker/view_presentation", new ViewPresentationCommand());
        commandMap.put("/speaker/view_conferences", new ViewConferencesCommand());



        commandMap.put("/user/cabinet", new UserCabinetCommand());
    }

    public static CommandFactory getCommandFactory() {
        return commandFactory;
    }

    public Command createCommand(HttpServletRequest request) {
        String url = request.getRequestURI().substring(request.getContextPath().length());
        Command command = commandMap.get(url);
        if (Objects.isNull(command)) {
            return new ErrorCommand();
        }
        return command;
    }
}
