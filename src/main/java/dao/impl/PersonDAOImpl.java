package dao.impl;

import config.database.ConnectorDB;
import exception.EntityNotCreateException;
import exception.EntityNotFoundException;
import model.entity.PersonDetails;
import util.query.PersonSql;
import dao.PersonDAO;
import dao.PersonDetailDAO;
import model.entity.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;


public class PersonDAOImpl implements PersonDAO {

    private final Logger logger = Logger.getLogger(PersonDAOImpl.class.getSimpleName());

    private PersonDetailDAO personDetailDAO;

    public PersonDAOImpl(PersonDetailDAO personDetailDAO) {
        this.personDetailDAO = personDetailDAO;
    }

    public PersonDAOImpl() {

    }


    @Override
    public Person create(Person person) throws EntityNotCreateException {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection != null ? connection.prepareStatement
                     (PersonSql.SQL_QUERY_PERSON_INSERT,
                             PreparedStatement.RETURN_GENERATED_KEYS) : null) {
            assert statement != null;
            statement.setObject(1, person.getId());
            statement.setString(2, person.getLogin());
            statement.setString(3, person.getPassword());
            statement.setObject(4, person.getDetails());
            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                person.setId(rs.getObject("id", UUID.class));
                return person;
            }
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }
        throw new EntityNotCreateException("Person not create!");
    }

    @Override
    public Person findById(UUID id) {
        Person person;
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection != null ? connection.prepareStatement
                     (PersonSql.SQL_QUERY_PERSON_GET) : null) {
            assert statement != null;
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                person = new Person();
                person.setId(rs.getObject("person_id", UUID.class));
                person.setLogin(rs.getString("login"));
                person.setPassword(rs.getString("password"));
                person.setDetails((PersonDetails) rs.getObject("person_details_fk"));
                return person;
            }
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }
        throw new EntityNotFoundException("Person not found!");
    }

    @Override
    public Person update(Person person) throws EntityNotCreateException {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection != null ? connection.prepareStatement
                     (PersonSql.SQL_QUERY_PERSON_UPDATE) : null) {
            assert statement != null;
            statement.setObject(1, person.getId());
            statement.setString(2, person.getLogin());
            statement.setString(3,person.getPassword());
            statement.setObject(4, person.getDetails());
            statement.execute();

        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }
        throw new EntityNotCreateException("Person not create!");
    }

    @Override
    public List<Person> findAll() {
        List<Person> personList = null;
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection != null ? connection.prepareStatement
                     (PersonSql.SQL_QUERY_PERSON_GET_ALL) : null) {

            assert statement != null;
            ResultSet rs = statement.executeQuery();

            personList = new ArrayList<>();

            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getObject("person_id", UUID.class));
                person.setLogin(rs.getString("login"));
                person.setPassword(rs.getString("password"));
                person.setDetails((PersonDetails) rs.getObject("person_detail_fk"));

                personList.add(person);
            }
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }
        return personList;
    }


    @Override
    public boolean delete(UUID id) {
        if (findById(id) != null) {
            try (Connection connection = ConnectorDB.getConnection();
                 PreparedStatement statement = connection != null ? connection.prepareStatement
                         (PersonSql.SQL_QUERY_PERSON_DELETE) : null) {
                assert statement != null;
                statement.setObject(1, id);
                statement.execute();
            } catch (SQLException e) {
                logger.warning(e.getMessage());
            }
        }
        throw new EntityNotFoundException("Person not found!");
    }

    @Override
    public boolean deleteAll() {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection != null ? connection.prepareStatement
                     (PersonSql.SQL_QUERY_PERSON_DELETE_ALL) : null) {
            assert statement != null;
            statement.execute();
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }
        throw new EntityNotFoundException("NOT!");
    }
}
