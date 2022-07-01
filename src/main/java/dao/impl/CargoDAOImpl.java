package dao.impl;

import config.database.CargoSql;
import config.database.ConnectorDB;
import dao.CargoDAO;
import dao.PersonDAO;
import model.entity.Cargo;
import model.entity.enums.CargoState;
import model.entity.enums.CargoType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CargoDAOImpl implements CargoDAO {

    PersonDAO personDAO;

    private void resultSet(Cargo cargo, ResultSet rs) throws SQLException {
        cargo.setId(rs.getObject("id", UUID.class));
        cargo.setName(rs.getString("name"));
        cargo.setDescription(rs.getString("description"));
        cargo.setType(CargoType.valueOf(rs.getString("type")));
        cargo.setState(CargoState.valueOf(rs.getString("state")));
        cargo.setWeight(rs.getDouble("weight"));
        cargo.setVolume(rs.getDouble("volume"));
        cargo.setCreatedAt((LocalDateTime) rs.getObject("create_at"));
        cargo.setModifiedAt((LocalDateTime) rs.getObject("modified_at"));
        cargo.setOwner(personDAO.findById(rs.getObject("id", UUID.class)));
    }

    private void statement(Cargo cargo, PreparedStatement statement) throws SQLException {
        statement.setObject(1, cargo.getId());
        statement.setString(2, cargo.getDescription());
        statement.setString(3, cargo.getDescription());
        statement.setString(4, String.valueOf(cargo.getType()));
        statement.setString(5, String.valueOf(cargo.getState()));
        statement.setDouble(6, cargo.getWeight());
        statement.setDouble(7, cargo.getVolume());
        statement.setObject(8, cargo.getCreatedAt());
        statement.setObject(9, cargo.getModifiedAt());
        statement.setObject(10, cargo.getOwner());
        statement.execute();
    }


    @Override
    public Cargo create(Cargo cargo) {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (CargoSql.SQL_QUERY_CARGO_INSERT,
                             PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement(cargo, statement);

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                cargo.setId(rs.getObject("id", UUID.class));
                return cargo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //todo if in row 44 false, cargo id == null
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
                cargo = new Cargo();
                resultSet(cargo, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cargo;
    }


    @Override
    public Cargo update(Cargo cargo) {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (CargoSql.SQL_QUERY_CARGO_UPDATE)) {
            statement(cargo, statement);


            Cargo fromBase = findById(cargo.getId());
            fromBase.setOwner(personDAO.update(cargo.getOwner()));
        } catch (SQLException e) {
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
                Cargo cargo = new Cargo();
                resultSet(cargo, rs);

                cargoList.add(cargo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean deleteAll() {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement
                     (CargoSql.SQL_QUERY_CARGO_DELETE_ALL)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
