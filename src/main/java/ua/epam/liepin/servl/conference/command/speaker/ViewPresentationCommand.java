package ua.epam.liepin.servl.conference.command.speaker;

import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.constant.Constants;
import ua.epam.liepin.servl.conference.entity.Presentation;
import ua.epam.liepin.servl.conference.factory.ServiceFactory;
import ua.epam.liepin.servl.conference.service.PresentationService;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewPresentationCommand implements Command {
    private final PresentationService presentationService;

    public ViewPresentationCommand() {
        presentationService = ServiceFactory.getInstance().getPresentationService();
    }

    @Override
    public String execute(HttpServletRequest request) {

        int page = Constants.ONE;
        int recordsPerPage = Constants.SIX;

        if (request.getParameter(Constants.PAGE) != null) {
            page = Integer.parseInt(request.getParameter(Constants.PAGE));
        }
        List<Presentation> presentations = presentationService.findAll((page - Constants.ONE) * recordsPerPage, recordsPerPage);
        int noOfRecords = presentationService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * Constants.ONE_DOUBLE / recordsPerPage);

        request.setAttribute("presentations", presentations);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        return "/speaker/view_presentation.jsp";
    }
}
