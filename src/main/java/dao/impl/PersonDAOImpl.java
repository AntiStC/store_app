package dao.impl;

import config.database.ConnectorDB;
import dao.CargoDAO;
import dao.PersonDAO;
import dao.PersonDetailDAO;
import model.entity.Person;
import model.entity.PersonDetails;
import util.query.PersonSql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class PersonDAOImpl implements PersonDAO {

    // TODO: 03.07.2022 init?
    private final PersonDetailDAO personDetailDAO;
    private final CargoDAO cargoDAO;

    // TODO: 06.07.2022 DI init
    public PersonDAOImpl() {
        this.cargoDAO = new CargoDAOImpl();
        this.personDetailDAO = new PersonDetailDAOImpl();
    }

    @Override
    public Person create(Person person) {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonSql.SQL_QUERY_PERSON_INSERT,
                             PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, person.getLogin());
            statement.setString(2, person.getPassword());
            statement.setObject(3, personDetailDAO.create(person.getDetails()).getId());
            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                person.setId(rs.getObject("id", UUID.class));
                person.getCargoList().forEach(cargo -> {
                    cargo.setOwner(person);
                    cargoDAO.create(cargo);
                });
                return person;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Person findById(UUID id) {
        Person person = null;
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonSql.SQL_QUERY_PERSON_GET)) {
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                person = new Person();
                person.setId(rs.getObject("id", UUID.class));
                person.setLogin(rs.getString("login"));
                person.setPassword(rs.getString("password"));
                PersonDetails personDetails = new PersonDetails.Builder()
                        .setId(rs.getObject("pd_id", UUID.class))
                        .setFirstName(rs.getString("first_name"))
                        .setLastName(rs.getString("last_name"))
                        .setPassportNum(rs.getInt("passport_num"))
                        .setAddress(rs.getString("address"))
                        .build();
                person.setDetails(personDetails);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Person update(Person person) {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonSql.SQL_QUERY_PERSON_UPDATE)) {
            statement.setObject(1, person.getLogin());
            statement.setObject(2, person.getPassword());
            statement.setObject(3, person.getId());
            statement.execute();

            personDetailDAO.update(person.getDetails());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public List<Person> findAll() {
        List<Person> personList = null;
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonSql.SQL_QUERY_PERSON_GET_ALL)) {

            ResultSet rs = statement.executeQuery();

            personList = new ArrayList<>();

            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getObject("id", UUID.class));
                person.setLogin(rs.getString("login"));
                person.setPassword(rs.getString("password"));
                person.setDetails(personDetailDAO.findById(rs.getObject("person_detail_id", UUID.class)));

                personList.add(person);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return personList;
    }


    @Override
    public void delete(UUID id) {
        if (findById(id) != null) {
            try (Connection connection = ConnectorDB.getConnection();
                 PreparedStatement statement = connection.prepareStatement
                         (PersonSql.SQL_QUERY_PERSON_DELETE)) {
                statement.setObject(1, id);
                statement.execute();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteAll() {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonSql.SQL_QUERY_PERSON_DELETE_ALL)) {
            statement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
