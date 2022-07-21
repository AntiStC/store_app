package dao.impl;

import config.database.ConnectorDB;
import exception.EntityNotCreateException;
import exception.EntityNotFoundException;
import model.entity.Cargo;
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

    public PersonDetailDAOImpl() {
    }

    private void executeStatement(PersonDetails personDetails, PreparedStatement statement)
            throws SQLException {
        statement.setObject(1, personDetails.getId());
        statement.setString(2, personDetails.getFirstName());
        statement.setString(3, personDetails.getLastName());
        statement.setInt(4, personDetails.getPassportNum());
        statement.setString(5, personDetails.getAddress());
        statement.executeUpdate();
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
    public PersonDetails create(PersonDetails personDetails) throws EntityNotCreateException {
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
        } catch (SQLException e) {
        }
        throw new EntityNotCreateException("Person detail not create!");
    }


    @Override
    public PersonDetails findById(UUID id) {
        PersonDetails personDetails;
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonDetailSql.SQL_QUERY_PERSON_DETAIL_GET)) {
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                personDetails = createPersonDetails(rs);
                return personDetails;
            }
        } catch (SQLException e) {
        }
        throw new EntityNotFoundException("Person detail not found!");
    }

    @Override
    public PersonDetails update(PersonDetails personDetails) throws EntityNotCreateException {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonDetailSql.SQL_QUERY_PERSON_DETAIL_UPDATE)) {
            executeStatement(personDetails, statement);

            PersonDetails fromBasePD = findById(personDetails.getId());
            if (fromBasePD != null) {
                return fromBasePD;
            }

        } catch (SQLException e) {
        }
        throw new EntityNotCreateException("Person detail not create!");
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
                PersonDetails personDetails = createPersonDetails(rs);
                personDetailsList.add(personDetails);
            }
        } catch (SQLException e) {
        }
        return personDetailsList;
    }


    @Override
    public boolean delete(UUID id) {
        if (findById(id) != null) {
            try (Connection connection = ConnectorDB.getConnection();
                 PreparedStatement statement = connection.prepareStatement
                         (PersonDetailSql.SQL_QUERY_PERSON_DETAIL_DELETE)) {
                statement.setObject(1, id);
                statement.executeUpdate();
            } catch (SQLException e) {
            }
        }
        throw new EntityNotFoundException("Person detail not found!");
    }

    @Override
    public boolean deleteAll() {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (PersonDetailSql.SQL_QUERY_PERSON_DETAIL_DELETE_ALL)) {
            statement.executeUpdate();
        } catch (SQLException e) {
        }
        throw new EntityNotFoundException("NOT!");
    }
}
