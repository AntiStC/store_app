package dao.impl;

import config.database.ConnectorDB;
import util.query.PersonSql;
import dao.CargoDAO;
import dao.PersonDAO;
import dao.PersonDetailDAO;
import model.entity.Cargo;
import model.entity.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class PersonDAOImpl implements PersonDAO {

    // TODO: 03.07.2022 init?
    CargoDAO cargoDAO;
    PersonDetailDAO personDetailDAO;

    @Override
    public Person create(Person person) {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonSql.SQL_QUERY_PERSON_INSERT,
                             PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setObject(1, person.getId());
            // TODO: 01.07.2022 add login & password fields
            statement.setObject(2, person.getDetails());
            statement.setObject(3, person.getCargoList());
            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                person.setId(rs.getObject("id", UUID.class));
                return person;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectorDB.closeConnection();
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
                // TODO: 01.07.2022 add login & password fields
                person.setDetails(personDetailDAO.findById(rs.getObject("id", UUID.class)));
                //todo use loop or stream
                person.addCargos((List<Cargo>) cargoDAO.findById
                        (rs.getObject("id", UUID.class)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectorDB.closeConnection();
        }
        return person;
    }

    @Override
    public Person update(Person person) {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonSql.SQL_QUERY_PERSON_UPDATE)) {
            statement.setObject(1, person.getId());
            statement.setObject(2, person.getDetails());
            statement.setObject(3, person.getCargoList());
            statement.execute();


            Person fromBase = findById(person.getId());
            //todo use loop or stream
            fromBase.addCargos((List<Cargo>) cargoDAO.update((Cargo) person.getCargoList()));
            fromBase.setDetails(personDetailDAO.update(person.getDetails()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectorDB.closeConnection();
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
                // TODO: 01.07.2022 add login & password fields
                person.setDetails(personDetailDAO.findById(rs.getObject("id", UUID.class)));
                //todo use loop or stream
                person.addCargos((List<Cargo>) cargoDAO.findById(rs.getObject
                        ("id", UUID.class)));

                personList.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectorDB.closeConnection();
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
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnectorDB.closeConnection();
            }
        }
    }

    @Override
    public void deleteAll() {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonSql.SQL_QUERY_PERSON_DELETE_ALL)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectorDB.closeConnection();
        }
    }
}
