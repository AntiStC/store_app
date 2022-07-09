package dao.impl;

import config.database.ConnectorDB;
import util.query.PersonDetailSql;
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

    private void executeStatement(PersonDetails personDetails, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, personDetails.getFirstName());
        statement.setString(2, personDetails.getLastName());
        statement.setInt(3, personDetails.getPassportNum());
        statement.setString(4, personDetails.getAddress());
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
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonDetailSql.SQL_QUERY_PERSON_DETAIL_INSERT,
                             PreparedStatement.RETURN_GENERATED_KEYS)) {
            executeStatement(personDetails, statement);

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                personDetails.setId(rs.getObject("id", UUID.class));
                return personDetails;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
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
                personDetails = createPersonDetails(rs);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return personDetails;
    }

    @Override
    public PersonDetails update(PersonDetails personDetails) {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonDetailSql.SQL_QUERY_PERSON_DETAIL_UPDATE)) {
            statement.setString(1, personDetails.getFirstName());
            statement.setString(2, personDetails.getLastName());
            statement.setInt(3, personDetails.getPassportNum());
            statement.setString(4, personDetails.getAddress());
            statement.setObject(5, personDetails.getId());
            statement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return personDetails;
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
                personDetailsList.add(createPersonDetails(rs));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
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
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteAll() {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonDetailSql.SQL_QUERY_PERSON_DETAIL_DELETE_ALL)) {
            statement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
