package dao.impl;

import config.database.ConnectorDB;
import dao.CargoDAO;
import dao.PersonDAO;
import exception.EntityNotCreateException;
import exception.EntityNotFoundException;
import model.entity.Cargo;
import model.entity.CargoState;
import model.entity.CargoType;
import util.query.CargoSql;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CargoDAOImpl implements CargoDAO {



    public CargoDAOImpl() {
    }


    private Cargo createCargo(ResultSet rs) throws SQLException {

        return new Cargo.Builder()
                .setId(rs.getObject("id", UUID.class))
                .setName(rs.getString("name"))
                .setDescription(rs.getString("description"))
                .setType(CargoType.valueOf(rs.getString("type")))
                .setState(CargoState.valueOf(rs.getString("state")))
                .setWeight(rs.getDouble("weight"))
                .setVolume(rs.getDouble("volume"))

                .setCreatedAt((LocalDateTime) rs.getObject("create_at"))
                .setModifiedAt((LocalDateTime) rs.getObject("modified_at"))

                .build();
    }

    private void executeStatement(Cargo cargo, PreparedStatement statement) throws SQLException {
        statement.setString(1, cargo.getName());
        statement.setString(2, cargo.getDescription());

        statement.setString(3, cargo.getDescription());
        statement.setString(4, String.valueOf(cargo.getType()));
        statement.setString(5, String.valueOf(cargo.getState()));
        statement.setDouble(6, cargo.getWeight());
        statement.setDouble(7, cargo.getVolume());
        statement.setObject(8, cargo.getCreatedAt());
        statement.setObject(9, cargo.getModifiedAt());
        statement.executeUpdate();

    }


    @Override
    public Cargo create(Cargo cargo) throws EntityNotCreateException {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (CargoSql.SQL_QUERY_CARGO_INSERT,
                             PreparedStatement.RETURN_GENERATED_KEYS)) {
            executeStatement(cargo, statement);

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                cargo.setId(rs.getObject("id", UUID.class));
                return cargo;
            }
        } catch (SQLException e) {
        }

        throw new EntityNotCreateException("Cargo not create!");
    }


    @Override
    public Cargo findById(UUID id) {
        Cargo cargo;
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (CargoSql.SQL_QUERY_CARGO_GET)) {
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                cargo = createCargo(rs);
                return cargo;
            }
        } catch (SQLException e) {
        }

        throw new EntityNotFoundException("Entity cargo not found!");
    }

    public List<Cargo> findByPersonId(UUID id) {
       List<Cargo> cargoList = new ArrayList<>();
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (CargoSql.SQL_QUERY_CARGO_GET_BY_PERSON_ID)) {
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
               Cargo cargo = createCargo(rs);
               cargoList.add(cargo);
            }

            return cargoList;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // TODO: 08.07.2022 throw exception
        return null;
    }


    @Override
    public Cargo update(Cargo cargo) throws EntityNotCreateException {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (CargoSql.SQL_QUERY_CARGO_UPDATE)) {
            executeStatement(cargo, statement);
            Cargo fromBase = findById(cargo.getId());
            if (fromBase != null) {
                return fromBase;
            }
        } catch (SQLException e) {

        }

        throw new EntityNotCreateException("Cargo not update!");
    }

    @Override
    public List<Cargo> findAll() {
        List<Cargo> cargoList = null;
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (CargoSql.SQL_QUERY_CARGO_GET_ALL)) {

            ResultSet rs = statement.executeQuery();

            cargoList = new ArrayList<>();

            while (rs.next()) {
                Cargo cargo = createCargo(rs);

                cargoList.add(cargo);
            }
        } catch (SQLException e) {
        }

        return cargoList;
    }


    @Override
    public boolean delete(UUID id) {
        if (findById(id) != null) {
            try (Connection connection = ConnectorDB.getConnection();
                 PreparedStatement statement = connection.prepareStatement
                         (CargoSql.SQL_QUERY_CARGO_DELETE)) {
                statement.setObject(1, id);
                statement.executeUpdate();
                return true;
            } catch (SQLException e) {
            }
        }
        throw new EntityNotFoundException("Cargo not found!");
    }

    @Override
    public boolean deleteAll() {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (CargoSql.SQL_QUERY_CARGO_DELETE_ALL)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new EntityNotFoundException("NOT!");
    }
}
