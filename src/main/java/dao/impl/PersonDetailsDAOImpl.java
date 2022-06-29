package dao.impl;

import config.database.ConnectorDB;
import dao.PersonDetailsDAO;
import model.entity.PersonDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class PersonDetailsDAOImpl implements PersonDetailsDAO {
    public static final String get = "SELECT id, first_name, last_name, " +
            "passport_num, address FROM person_details WHERE id = (?)";
    public static final String getAll = "SELECT id, first_name, last_name, " +
            "passport_num, address FROM person_details";
    public static final String insert = "INSERT INTO person_details(id, first_name, " +
            "last_name, passport_num, address)" +
            " VALUES(uuid_generate_v4(),(?),(?),(?)) RETURNING id";
    public static final String delete = "DELETE FROM person_details WHERE id = (?)";
    public static final String deleteAll = "TRUNCATE person_details CASCADE";
    public static final String update = "UPDATE person_details SET first_name = (?), " +
            "last_name = (?), passport_num = (?), address = (?) WHERE id = (?) RETURNING id";

    @Override
    public PersonDetails create(PersonDetails personDetails) {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (insert, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setObject(1, personDetails.getId());
            statement.setString(2, personDetails.getFirstName());
            statement.setString(3, personDetails.getLastName());
            statement.setInt(4, personDetails.getPassportNum());
            statement.setString(5, personDetails.getAddress());
            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                personDetails.setId(rs.getObject("id", UUID.class));
                return personDetails;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectorDB.closeConnection();
        }
        return personDetails;
    }

    @Override
    public PersonDetails findById(UUID id) {
        PersonDetails personDetails = null;
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(get)) {
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                personDetails = new PersonDetails();
                personDetails.setId(rs.getObject("id", UUID.class));
                personDetails.setFirstName(rs.getString("first_name"));
                personDetails.setLastName(rs.getString("last_name"));
                personDetails.setPassportNum(rs.getInt("passport_num"));
                personDetails.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectorDB.closeConnection();
        }
        return personDetails;
    }

    @Override
    public PersonDetails update(PersonDetails personDetailsDto) {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(update)) {
            statement.setObject(1, personDetailsDto.getId());
            statement.setString(2, personDetailsDto.getFirstName());
            statement.setString(3, personDetailsDto.getLastName());
            statement.setInt(4, personDetailsDto.getPassportNum());
            statement.setString(5, personDetailsDto.getAddress());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectorDB.closeConnection();
        }
        return personDetailsDto;
    }

    @Override
    public List<PersonDetails> findAll() {
        List<PersonDetails> personDetailsList = null;
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(getAll)) {

            ResultSet rs = statement.executeQuery();

            personDetailsList = new ArrayList<>();

            while (rs.next()) {
                PersonDetails personDetailsDto = new PersonDetails();
                personDetailsDto.setId(rs.getObject("id", UUID.class));
                personDetailsDto.setFirstName(rs.getString("name"));
                personDetailsDto.setLastName(rs.getString("description"));
                personDetailsDto.setPassportNum(rs.getInt("type"));
                personDetailsDto.setAddress(rs.getString("state"));

                personDetailsList.add(personDetailsDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectorDB.closeConnection();
        }
        return personDetailsList;
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
