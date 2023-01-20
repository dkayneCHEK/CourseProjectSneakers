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

public class UpdateShoeServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DatabaseManager.initDB();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String articul = req.getParameter("articul");
        String name = req.getParameter("name");
        String brand = req.getParameter("brand");
        String color = req.getParameter("color");
        Double price = Double.valueOf(req.getParameter("price"));
        Double sale = Double.valueOf(req.getParameter("sale"));
        String picture = req.getParameter("picture");
        String about = req.getParameter("about");

        Shoe shoe = new Shoe(id, name, price, about, articul, brand, color, sale, picture);

        ShoeServiceImpl shoeService = new ShoeServiceImpl();
        shoeService.updateShoe(shoe);
    }
}

