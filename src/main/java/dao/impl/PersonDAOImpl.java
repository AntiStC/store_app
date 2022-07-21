package dao.impl;

import config.database.ConnectorDB;
import dao.PersonDAO;
import dao.PersonDetailDAO;
import exception.EntityNotCreateException;
import exception.EntityNotFoundException;
import model.entity.Person;
import util.query.PersonSql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class PersonDAOImpl implements PersonDAO {

    private PersonDetailDAO personDetailDAO;


    @Override
    public Person create(Person person) throws EntityNotCreateException {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonSql.SQL_QUERY_PERSON_INSERT,
                             PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setObject(1, person.getId());
            statement.setString(2, person.getLogin());
            statement.setString(3, person.getPassword());
            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                person.setId(rs.getObject("id", UUID.class));
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
            statement.setObject(1, person.getId());
            statement.setString(2, person.getLogin());
            statement.setString(3, person.getPassword());
            statement.executeUpdate();

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
                statement.executeUpdate();
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
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new EntityNotFoundException("NOT!");
    }
}
