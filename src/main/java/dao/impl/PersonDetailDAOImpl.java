package dao.impl;

import config.database.ConnectorDB;
import config.database.PersonDetailSql;
import dao.PersonDetailDAO;
import model.entity.PersonDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class PersonDetailDAOImpl implements PersonDetailDAO {

    private void statement(PersonDetails personDetails, PreparedStatement statement)
            throws SQLException {
        statement.setObject(1, personDetails.getId());
        statement.setString(2, personDetails.getFirstName());
        statement.setString(3, personDetails.getLastName());
        statement.setInt(4, personDetails.getPassportNum());
        statement.setString(5, personDetails.getAddress());
        statement.execute();
    }

    @Override
    public PersonDetails create(PersonDetails personDetails) {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonDetailSql.SQL_QUERY_PERSON_DETAIL_INSERT,
                             PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement(personDetails, statement);

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
             PreparedStatement statement = connection.prepareStatement
                     (PersonDetailSql.SQL_QUERY_PERSON_DETAIL_GET)) {
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
             PreparedStatement statement = connection.prepareStatement
                     (PersonDetailSql.SQL_QUERY_PERSON_DETAIL_UPDATE)) {
            statement(personDetailsDto, statement);

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
             PreparedStatement statement = connection.prepareStatement
                     (PersonDetailSql.SQL_QUERY_PERSON_DETAIL_GET_ALL)) {

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
                 PreparedStatement statement = connection.prepareStatement
                         (PersonDetailSql.SQL_QUERY_PERSON_DETAIL_DELETE)) {
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
                     (PersonDetailSql.SQL_QUERY_PERSON_DETAIL_DELETE_ALL)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectorDB.closeConnection();
        }
    }
}
