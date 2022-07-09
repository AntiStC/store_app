package controller;

import service.PersonService;
import util.query.Parser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet(name = "PersonController", value = "/persons/*")
public class PersonController extends HttpServlet {
    private PersonService personService;

    @Override
    public void init() throws ServletException {
        super.init();
        personService = new PersonService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF8");

        try (PrintWriter pw = response.getWriter()) {
            UUID id = Parser.getIdFromPath(request.getRequestURI());
            if (id != null) {
                pw.println(personService.read(id));
            } else {
                pw.println(personService.getAll());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // TODO: 09.07.2022 implement saveOrUpdate() ???
        personService.create(Parser.parseEntry(request.getReader()));
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
