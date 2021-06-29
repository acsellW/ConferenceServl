package ua.epam.liepin.servl.conference.command.user;

import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.constant.Constants;
import ua.epam.liepin.servl.conference.dto.PresentationDTO;
import ua.epam.liepin.servl.conference.dto.UserConferenceDTO;
import ua.epam.liepin.servl.conference.entity.Conference;
import ua.epam.liepin.servl.conference.entity.Presentation;
import ua.epam.liepin.servl.conference.entity.User;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.ConferenceService;
import ua.epam.liepin.servl.conference.service.PresentationService;
import ua.epam.liepin.servl.conference.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class UserViewConference implements Command {
    private final ConferenceService conferenceService;
    private final UserService userService;
    private final PresentationService presentationService;

    public UserViewConference() {
        conferenceService = ServiceFactory.getInstance().getConferenceService();
        userService = ServiceFactory.getInstance().getUserService();
        presentationService = ServiceFactory.getInstance().getPresentationService();
    }

    @Override
    public String execute(HttpServletRequest request) {

        int conferenceId = Integer.parseInt(request.getParameter("conferenceId"));
        Conference conference = conferenceService.findById(conferenceId);
        User curentUser = (User) request.getSession().getAttribute(Constants.USER);
        boolean isPresent = conferenceService.checkUserPresence(curentUser.getId(), conferenceId);

        UserConferenceDTO conferenceDTO = new UserConferenceDTO(conference, isPresent);
        request.setAttribute("conference", conferenceDTO);

        List<Presentation> presentations = conferenceService.getPresentationsFromConference(conferenceId);
        List<PresentationDTO> presentationsDTO = new ArrayList<>();
        User tempUs;
        PresentationDTO tempPres;
        StringBuilder usName = new StringBuilder();
        for (Presentation pres : presentations) {
            tempUs = userService.findById(pres.getSpeakerId());
            usName.append(tempUs.getName()).append(" ").append(tempUs.getSurname());
            tempPres = new PresentationDTO(pres, usName.toString());
            presentationsDTO.add(tempPres);
            usName.setLength(0);
        }
        request.setAttribute("presentations", presentationsDTO);

        return "/user/view_conference.jsp";
    }
}
