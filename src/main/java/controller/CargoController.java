package controller;

import service.CargoService;
import util.query.Parser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet(name = "CargoController", value = "/cargos/*")
public class CargoController extends HttpServlet {
    private CargoService cargoService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF8");
        System.out.println("CargoController");
        try (PrintWriter pw = response.getWriter()) {
            UUID id = Parser.getIdFromPath(request.getRequestURI());
            if (id != null) {
                pw.println(cargoService.findByPersonId(id));
            } else {
                pw.println(cargoService.getAll());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    public void init() throws ServletException {
        super.init();
        cargoService = new CargoService();
    }
}
