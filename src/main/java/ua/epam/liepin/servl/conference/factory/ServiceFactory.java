package ua.epam.liepin.servl.conference.factory;

import ua.epam.liepin.servl.conference.service.ConferenceService;
import ua.epam.liepin.servl.conference.service.PresentationService;
import ua.epam.liepin.servl.conference.service.UserService;
import ua.epam.liepin.servl.conference.service.impl.ConferenceServiceImpl;
import ua.epam.liepin.servl.conference.service.impl.PresentationServiceImpl;
import ua.epam.liepin.servl.conference.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory SERVICE_FACTORY = new ServiceFactory();

    private final UserService userService;
    private final PresentationService presentationService;
    private final ConferenceService conferenceService;

    private ServiceFactory() {
        userService = new UserServiceImpl();
        presentationService = new PresentationServiceImpl();
        conferenceService = new ConferenceServiceImpl();
    }

    public static ServiceFactory getInstance() {
        return SERVICE_FACTORY;
    }

    public UserService getUserService() {
        return userService;
    }

    public PresentationService getPresentationService() {
        return presentationService;
    }

    public ConferenceService getConferenceService() {
        return conferenceService;
    }
}
