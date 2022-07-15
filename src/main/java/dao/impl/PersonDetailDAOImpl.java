package dao.impl;

import config.database.ConnectorDB;
import util.query.PersonDetailSql;
import dao.PersonDetailDAO;
import model.entity.PersonDetails;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class PersonDetailDAOImpl implements PersonDetailDAO {

    private void executeStatement(PersonDetails personDetails, PreparedStatement statement)
            throws SQLException {
        statement.setObject(1, personDetails.getId());
        statement.setString(2, personDetails.getFirstName());
        statement.setString(3, personDetails.getLastName());
        statement.setInt(4, personDetails.getPassportNum());
        statement.setString(5, personDetails.getAddress());
        statement.execute();
    }

    private PersonDetails createPersonDetails(ResultSet rs) throws SQLException {
        return new PersonDetails.Builder()
                .setId(rs.getObject("id", UUID.class))
                .setFirstName(rs.getString("first_name"))
                .setLastName(rs.getString("last_name"))
                .setPassportNum(rs.getInt("passport_num"))
                .setAddress(rs.getString("address"))
                .build();
    }

    @Override
    public PersonDetails create(PersonDetails personDetails) {
        try (Connection connection = ConnectorDB.getPGDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonDetailSql.SQL_QUERY_PERSON_DETAIL_INSERT,
                             PreparedStatement.RETURN_GENERATED_KEYS)) {
            executeStatement(personDetails, statement);

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                personDetails.setId(rs.getObject("id", UUID.class));
                return personDetails;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public PersonDetails findById(UUID id) {
        PersonDetails personDetails = null;
        try (Connection connection = ConnectorDB.getPGDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonDetailSql.SQL_QUERY_PERSON_DETAIL_GET)) {
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                personDetails = createPersonDetails(rs);
                return personDetails;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PersonDetails update(PersonDetails personDetailsDto) {
        try (Connection connection = ConnectorDB.getPGDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonDetailSql.SQL_QUERY_PERSON_DETAIL_UPDATE)) {
            executeStatement(personDetailsDto, statement);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return personDetailsDto;
    }

    @Override
    public List<PersonDetails> findAll() {
        List<PersonDetails> personDetailsList = null;
        try (Connection connection = ConnectorDB.getPGDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonDetailSql.SQL_QUERY_PERSON_DETAIL_GET_ALL)) {

            ResultSet rs = statement.executeQuery();

            personDetailsList = new ArrayList<>();

            while (rs.next()) {
                personDetailsList.add(createPersonDetails(rs));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return personDetailsList;
    }


    @Override
    public boolean delete(UUID id) {
        if (findById(id) != null) {
            try (Connection connection = ConnectorDB.getPGDataSource().getConnection();
                 PreparedStatement statement = connection.prepareStatement
                         (PersonDetailSql.SQL_QUERY_PERSON_DETAIL_DELETE)) {
                statement.setObject(1, id);
                statement.execute();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean deleteAll() {
        try (Connection connection = ConnectorDB.getPGDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonDetailSql.SQL_QUERY_PERSON_DETAIL_DELETE_ALL)) {
            statement.execute();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
