package ru.eltech.project.server.servlet.auth;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.eltech.project.api.data.Client;
import ru.eltech.project.api.services.AuthService;
import ru.eltech.project.server.DatabaseManager;
import ru.eltech.project.server.serviceimpl.AuthServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

public class RegistrationServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DatabaseManager.initDB();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String mail = req.getParameter("mail");


        PrintWriter writer = resp.getWriter();
        AuthService authService = new AuthServiceImpl();
        String id = authService.registration(
                new Client("", name, address, mail, phone),
                login,
                password
        );
        writer.println(id);
        writer.close();
    }
}
