package dao.impl;

import config.database.ConnectorDB;

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
    public Person create(Person person) throws EntityNotCreateException {
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
        } catch (SQLException e) {
        }
        throw new EntityNotCreateException("Person not create!");
    }

    @Override
    public Person findById(UUID id) {
        Person person;
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

                person.setDetails(personDetailDAO.findById(rs.getObject("id", UUID.class)));
                return person;

            }
        } catch (SQLException e) {
        }
        throw new EntityNotFoundException("Person not found!");
    }

    @Override
    public Person update(Person person) throws EntityNotCreateException {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonSql.SQL_QUERY_PERSON_UPDATE)) {
            statement.setObject(1, person.getLogin());
            statement.setObject(2, person.getPassword());
            statement.setObject(3, person.getId());
            statement.execute();

            //todo use loop or stream
        } catch (SQLException e) {

        }
        throw new EntityNotCreateException("Person not create!");
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
        } catch (SQLException e) {
        }
        return personList;
    }


    @Override
    public boolean delete(UUID id) {
        if (findById(id) != null) {
            try (Connection connection = ConnectorDB.getConnection();
                 PreparedStatement statement = connection.prepareStatement
                         (PersonSql.SQL_QUERY_PERSON_DELETE)) {
                statement.setObject(1, id);
                statement.execute();
            } catch (SQLException e) {
            }
        }
        throw new EntityNotFoundException("Person not found!");
    }

    @Override
    public boolean deleteAll() {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonSql.SQL_QUERY_PERSON_DELETE_ALL)) {
            statement.execute();
        } catch (SQLException e) {
        }
        throw new EntityNotFoundException("NOT!");
    }
}
