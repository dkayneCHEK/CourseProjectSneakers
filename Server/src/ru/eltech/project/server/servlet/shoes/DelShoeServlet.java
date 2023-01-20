package ru.eltech.project.server.servlet.shoes;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.eltech.project.api.data.Shoe;
import ru.eltech.project.server.DatabaseManager;
import ru.eltech.project.server.serviceimpl.ShoeServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

public class DelShoeServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DatabaseManager.initDB();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        ShoeServiceImpl shoeService = new ShoeServiceImpl();
        shoeService.delShoe(id);
    }
}

