package dao.impl;

import config.database.ConnectorDB;
import config.database.PersonDetailsSQL;
import dao.PersonDetailsDAO;
import entity.PersonDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class PersonDetailsDAOImpl implements PersonDetailsDAO {


    @Override
    public PersonDetails create(PersonDetails personDetails) {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonDetailsSQL.INSERT.QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
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
             PreparedStatement statement = connection.prepareStatement(PersonDetailsSQL.GET.QUERY)) {
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                personDetails = new PersonDetails();
                personDetails.setId(rs.getObject("id", UUID.class));
                personDetails.setFirstName(rs.getString("first_name"));
                personDetails.setLastName(rs.getString("last_name"));
                personDetails.setPassportNum(rs.getInt("passportNum"));
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
    public PersonDetails update(PersonDetails personDetails) {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(PersonDetailsSQL.UPDATE.QUERY)) {
            statement.setObject(1, personDetails.getId());
            statement.setString(2, personDetails.getFirstName());
            statement.setString(3, personDetails.getLastName());
            statement.setInt(4, personDetails.getPassportNum());
            statement.setString(5, personDetails.getAddress());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectorDB.closeConnection();
        }
        return personDetails;
    }

    @Override
    public List<PersonDetails> findAll() {
        List<PersonDetails> personDetailsList = null;
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(PersonDetailsSQL.GET_ALL.QUERY)) {

            ResultSet rs = statement.executeQuery();

            personDetailsList = new ArrayList<>();

            while (rs.next()) {
                PersonDetails personDetails = new PersonDetails();
                personDetails.setId(rs.getObject("id", UUID.class));
                personDetails.setFirstName(rs.getString("name"));
                personDetails.setLastName(rs.getString("description"));
                personDetails.setPassportNum(rs.getInt("type"));
                personDetails.setAddress(rs.getString("state"));

                personDetailsList.add(personDetails);
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
                 PreparedStatement statement = connection.prepareStatement(PersonDetailsSQL.DELETE.QUERY)) {
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
             PreparedStatement statement = connection.prepareStatement(PersonDetailsSQL.DELETE_ALL.QUERY)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectorDB.closeConnection();
        }
    }
}
