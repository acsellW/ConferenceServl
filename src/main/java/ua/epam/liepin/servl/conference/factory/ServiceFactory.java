package ua.epam.liepin.servl.conference.factory;

import ua.epam.liepin.servl.conference.service.UserService;
import ua.epam.liepin.servl.conference.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory SERVICE_FACTORY = new ServiceFactory();

    private final UserService userService;

    private ServiceFactory() {
        userService = new UserServiceImpl();
    }

    public static ServiceFactory getInstance() {
        return SERVICE_FACTORY;
    }

    public UserService getUserService() {
        return userService;
    }
}
