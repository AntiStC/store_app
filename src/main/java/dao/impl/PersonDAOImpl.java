package dao.impl;

import config.database.ConnectorDB;
import dao.CargoDAO;
import dao.PersonDAO;
import dao.PersonDetailsDAO;
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
    private static final String get = "SELECT id, details_id, cargos_id " +
            "FROM persons WHERE id = (?)";
    private static final String getAll = "SELECT id, details_id, cargos_id FROM persons";
    public static final String insert = "INSERT INTO persons (id, details_id, cargos_id) " +
            "VALUES(uuid_generate_v4(),(?),(?),(?)) RETURNING id";
    public static final String delete = "DELETE FROM persons WHERE id = (?)";
    public static final String deleteAll = "TRUNCATE persons CASCADE";
    public static final String update = "UPDATE persons SET details_id = (?), cargos_id = (?)" +
            " WHERE id = (?) RETURNING id";

    CargoDAO cargoDAO;
    PersonDetailsDAO personDetailsDAO;

    @Override
    public Person create(Person person) {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (insert, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setObject(1, person.getId());
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
             PreparedStatement statement = connection.prepareStatement(get)) {
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                person = new Person();
                person.setId(rs.getObject("id", UUID.class));
                person.setDetails(personDetailsDAO.findById(rs.getObject("id", UUID.class)));
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
             PreparedStatement statement = connection.prepareStatement(update)) {
            statement.setObject(1, person.getId());
            statement.setObject(2, person.getDetails());
            statement.setObject(3, person.getCargoList());
            statement.execute();


            Person fromBase = findById(person.getId());
            //todo use loop or stream
            fromBase.addCargos((List<Cargo>) cargoDAO.update((Cargo) person.getCargoList()));
            fromBase.setDetails(personDetailsDAO.update(person.getDetails()));
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
             PreparedStatement statement = connection.prepareStatement(getAll)) {

            ResultSet rs = statement.executeQuery();

            personList = new ArrayList<>();

            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getObject("id", UUID.class));
                person.setDetails(personDetailsDAO.findById(rs.getObject("id", UUID.class)));
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
                 PreparedStatement statement = connection.prepareStatement(delete)) {
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
             PreparedStatement statement = connection.prepareStatement(deleteAll)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectorDB.closeConnection();
        }
    }
}
