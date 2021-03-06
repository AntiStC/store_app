package dao.impl;

import config.database.ConnectorDB;
import dao.CargoDAO;
import dao.PersonDAO;
import exception.EntityNotCreateException;
import exception.EntityNotFoundException;
import model.entity.Cargo;
import model.entity.CargoState;
import model.entity.CargoType;
import model.entity.Person;
import util.query.CargoSql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;


public class CargoDAOImpl implements CargoDAO {
    private final Logger logger = Logger.getLogger(CargoDAOImpl.class.getSimpleName());

    private PersonDAO personDAO;

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
                .setOwner((Person) rs.getObject("person_fk"))
                .build();
    }

    private void executeStatement(Cargo cargo, PreparedStatement statement) throws SQLException {
        statement.setObject(1, cargo.getId());
        statement.setString(2, cargo.getDescription());
        statement.setString(3, cargo.getDescription());
        statement.setString(4, String.valueOf(cargo.getType()));
        statement.setString(5, String.valueOf(cargo.getState()));
        statement.setDouble(6, cargo.getWeight());
        statement.setDouble(7, cargo.getVolume());
        statement.setObject(8, cargo.getCreatedAt());
        statement.setObject(9, cargo.getModifiedAt());
        statement.setObject(10, cargo.getOwner().getId());
        statement.executeUpdate();
    }


    @Override
    public Cargo create(Cargo cargo) throws EntityNotCreateException {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection != null ? connection.prepareStatement
                     (CargoSql.SQL_QUERY_CARGO_INSERT,
                             PreparedStatement.RETURN_GENERATED_KEYS) : null) {
            assert statement != null;
            executeStatement(cargo, statement);

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                cargo.setId(rs.getObject("id", UUID.class));
                return cargo;
            }
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }

        throw new EntityNotCreateException("Cargo not create!");
    }


    @Override
    public Cargo findById(UUID id) {
        Cargo cargo;
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection != null ? connection.prepareStatement
                     (CargoSql.SQL_QUERY_CARGO_GET) : null) {
            assert statement != null;
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                cargo = createCargo(rs);
                return cargo;
            }
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }

        throw new EntityNotFoundException("Entity cargo not found!");
    }


    @Override
    public Cargo update(Cargo cargo) throws EntityNotCreateException {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection != null ? connection.prepareStatement
                     (CargoSql.SQL_QUERY_CARGO_UPDATE) : null) {
            assert statement != null;
            executeStatement(cargo, statement);

            Cargo fromBase = findById(cargo.getId());
            if (fromBase != null) {
                return fromBase;
            }
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }

        throw new EntityNotCreateException("Cargo not update!");
    }

    @Override
    public List<Cargo> findAll() {
        List<Cargo> cargoList = null;
        try (Connection connection = ConnectorDB.getConnection()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement
                    (CargoSql.SQL_QUERY_CARGO_GET_ALL)) {

                ResultSet rs = statement.executeQuery();

                cargoList = new ArrayList<>();

                while (rs.next()) {
                    Cargo cargo = createCargo(rs);

                    cargoList.add(cargo);
                }
            }
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }

        return cargoList;
    }


    @Override
    public boolean delete(UUID id) {
        if (findById(id) != null) {
            try (Connection connection = ConnectorDB.getConnection();
                 PreparedStatement statement = connection != null ? connection.prepareStatement
                         (CargoSql.SQL_QUERY_CARGO_DELETE) : null) {
                assert statement != null;
                statement.setObject(1, id);
                statement.executeUpdate();
                return true;
            } catch (SQLException e) {
                logger.warning(e.getMessage());
            }
        }
        throw new EntityNotFoundException("Cargo not found!");
    }

    @Override
    public boolean deleteAll() {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection != null ? connection.prepareStatement
                     (CargoSql.SQL_QUERY_CARGO_DELETE_ALL) : null) {
            assert statement != null;
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }
        throw new EntityNotFoundException("NOT!");
    }
}
