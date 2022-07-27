package controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.PersonDAO;
import dao.PersonDetailDAO;
import dao.impl.CargoDAOImpl;
import dao.impl.PersonDAOImpl;
import dao.impl.PersonDetailDAOImpl;
import model.dto.PersonDto;
import service.CargoService;
import service.PersonDetailsService;
import service.PersonService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import static java.lang.System.out;

@WebServlet(name = "PersonController", value = "/persons/*")
public class PersonController extends HttpServlet {
    private PersonService personService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PersonDetailDAO personDetailDAO = new PersonDetailDAOImpl();
        PersonDAO personDAO = new PersonDAOImpl(personDetailDAO);
        personService = new PersonService(personDAO, new PersonDetailsService(personDetailDAO), new CargoService(new CargoDAOImpl(personDAO)));
        PrintWriter pw = response.getWriter();

        try {
            pw.println("getRequestURI: " + getIdFromPath(request.getRequestURI()));
        } catch (IllegalArgumentException e) {
            pw.println("ID not found! \n" + e.getMessage());
        } finally {
            pw.flush();
            pw.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PersonDetailDAO personDetailDAO = new PersonDetailDAOImpl();
        PersonDAO personDAO = new PersonDAOImpl(personDetailDAO);
        personService = new PersonService(personDAO, new PersonDetailsService(personDetailDAO), new CargoService(new CargoDAOImpl(personDAO)));
        out.println("POST");

        BufferedReader reader = request.getReader();
        JsonObject object = JsonParser.parseReader(reader).getAsJsonObject();

        PersonDto personDto = new PersonDto.Builder()
                .setLogin(object.get("login").getAsString())
                .setPassword(object.get("password").getAsString())
                .build();
        out.println(personDto);

    }

    private UUID getIdFromPath(String path) {
        String[] split = path.split("/");

        if (split.length > 0) {
            return UUID.fromString(split[split.length - 1]);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
