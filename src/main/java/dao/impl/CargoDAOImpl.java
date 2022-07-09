package dao.impl;

import util.query.CargoSql;
import config.database.ConnectorDB;
import dao.CargoDAO;
import dao.PersonDAO;
import model.entity.Cargo;
import model.entity.CargoState;
import model.entity.CargoType;

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
        Timestamp createdAtTs = (Timestamp) rs.getObject("create_at");
        Timestamp modifiedAtTs = (Timestamp) rs.getObject("modified_at");

        return new Cargo.Builder()
                .setId(rs.getObject("id", UUID.class))
                .setName(rs.getString("name"))
                .setDescription(rs.getString("description"))
                .setType(CargoType.valueOf(rs.getString("type")))
                .setState(CargoState.valueOf(rs.getString("state")))
                .setWeight(rs.getDouble("weight"))
                .setVolume(rs.getDouble("volume"))
                .setCreatedAt(LocalDateTime.ofInstant(createdAtTs.toInstant(), ZoneOffset.ofHours(0)))
                .setModifiedAt(modifiedAtTs != null ? LocalDateTime.ofInstant(modifiedAtTs.toInstant(), ZoneOffset.ofHours(0)) : null)
                .build();
    }

    private void executeStatement(Cargo cargo, PreparedStatement statement) throws SQLException {
        statement.setString(1, cargo.getName());
        statement.setString(2, cargo.getDescription());
        statement.setString(3, String.valueOf(cargo.getType()));
        statement.setString(4, String.valueOf(cargo.getState()));
        statement.setDouble(5, cargo.getWeight());
        statement.setDouble(6, cargo.getVolume());
        statement.setObject(7, cargo.getOwner().getId());
        statement.execute();
    }


    @Override
    public Cargo create(Cargo cargo) {
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return cargo;
    }


    @Override
    public Cargo findById(UUID id) {
        Cargo cargo = null;
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (CargoSql.SQL_QUERY_CARGO_GET)) {
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                cargo = createCargo(rs);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return cargo;
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
    public Cargo update(Cargo cargo) {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (CargoSql.SQL_QUERY_CARGO_UPDATE)) {
            executeStatement(cargo, statement);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return cargo;
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return cargoList;
    }


    @Override
    public void delete(UUID id) {
        if (findById(id) != null) {
            try (Connection connection = ConnectorDB.getConnection();
                 PreparedStatement statement = connection.prepareStatement
                         (CargoSql.SQL_QUERY_CARGO_DELETE)) {
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
                     (CargoSql.SQL_QUERY_CARGO_DELETE_ALL)) {
            statement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
