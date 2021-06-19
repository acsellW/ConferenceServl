package ua.epam.liepin.servl.conference;

import ua.epam.liepin.servl.conference.command.Command;
import ua.epam.liepin.servl.conference.factory.CommandFactory;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class PageServlet extends HttpServlet {
    private static final CommandFactory COMMAND_FACTORY = CommandFactory.getCommandFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = COMMAND_FACTORY.createCommand(req);

        String path = command.execute(req);
        req.getRequestDispatcher(path).forward(req, resp);
    }
}