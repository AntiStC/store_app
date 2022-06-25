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
    public PersonDetails create(PersonDetails personDetailsDto) {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonDetailsSQL.INSERT.QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setObject(1, personDetailsDto.getId());
            statement.setString(2, personDetailsDto.getFirstName());
            statement.setString(3, personDetailsDto.getLastName());
            statement.setInt(4, personDetailsDto.getPassportNum());
            statement.setString(5, personDetailsDto.getAddress());
            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                personDetailsDto.setId(rs.getObject("id", UUID.class));
                return personDetailsDto;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectorDB.closeConnection();
        }
        return personDetailsDto;
    }

    @Override
    public PersonDetails findById(UUID id) {
        PersonDetails personDetailsDto = null;
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(PersonDetailsSQL.GET.QUERY)) {
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                personDetailsDto = new PersonDetails();
                personDetailsDto.setId(rs.getObject("id", UUID.class));
                personDetailsDto.setFirstName(rs.getString("first_name"));
                personDetailsDto.setLastName(rs.getString("last_name"));
                personDetailsDto.setPassportNum(rs.getInt("passportNum"));
                personDetailsDto.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectorDB.closeConnection();
        }
        return personDetailsDto;
    }

    @Override
    public PersonDetails update(PersonDetails personDetailsDto) {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(PersonDetailsSQL.UPDATE.QUERY)) {
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
        List<PersonDetails> personDetailsDtoList = null;
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(PersonDetailsSQL.GET_ALL.QUERY)) {

            ResultSet rs = statement.executeQuery();

            personDetailsDtoList = new ArrayList<>();

            while (rs.next()) {
                PersonDetails personDetailsDto = new PersonDetails();
                personDetailsDto.setId(rs.getObject("id", UUID.class));
                personDetailsDto.setFirstName(rs.getString("name"));
                personDetailsDto.setLastName(rs.getString("description"));
                personDetailsDto.setPassportNum(rs.getInt("type"));
                personDetailsDto.setAddress(rs.getString("state"));

                personDetailsDtoList.add(personDetailsDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectorDB.closeConnection();
        }
        return personDetailsDtoList;
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
