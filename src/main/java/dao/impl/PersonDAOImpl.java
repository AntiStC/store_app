package dao.impl;

import config.database.ConnectorDB;
import dao.PersonDAO;
import dao.PersonDetailDAO;
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

    // TODO: 03.07.2022 init?
    PersonDetailDAO personDetailDAO;

    @Override
    public Person create(Person person) {
        try (Connection connection = ConnectorDB.getPGDataSource().getConnection();
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
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Person findById(UUID id) {
        Person person;
        try (Connection connection = ConnectorDB.getPGDataSource().getConnection();
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
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Person update(Person person) {
        try (Connection connection = ConnectorDB.getPGDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonSql.SQL_QUERY_PERSON_UPDATE)) {
            statement.setObject(1, person.getId());
            statement.setObject(2, person.getDetails());
            statement.setObject(3, person.getCargoList());
            statement.execute();

            //todo use loop or stream
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public List<Person> findAll() {
        List<Person> personList = null;
        try (Connection connection = ConnectorDB.getPGDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonSql.SQL_QUERY_PERSON_GET_ALL)) {

            ResultSet rs = statement.executeQuery();

            personList = new ArrayList<>();

            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getObject("id", UUID.class));
                person.setDetails(personDetailDAO.findById(rs.getObject("id", UUID.class)));

                personList.add(person);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return personList;
    }


    @Override
    public void delete(UUID id) {
        if (findById(id) != null) {
            try (Connection connection = ConnectorDB.getPGDataSource().getConnection();
                 PreparedStatement statement = connection.prepareStatement
                         (PersonSql.SQL_QUERY_PERSON_DELETE)) {
                statement.setObject(1, id);
                statement.execute();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteAll() {
        try (Connection connection = ConnectorDB.getPGDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonSql.SQL_QUERY_PERSON_DELETE_ALL)) {
            statement.execute();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
