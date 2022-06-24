package dao.impl;

import config.database.ConnectorDB;
import config.database.CargoSQL;
import dao.CargoDAO;
import dao.PersonDAO;
import entity.Cargo;
import entity.enums.CargoState;
import entity.enums.CargoType;

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

    @Override
    public Cargo create(Cargo cargo) {
        try (Connection connection = ConnectorDB.getConnection(); PreparedStatement statement = connection.prepareStatement(CargoSQL.INSERT.QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
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

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                cargo.setId(rs.getObject("id", UUID.class));
                return cargo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectorDB.closeConnection();
        }
        return cargo;
    }

    @Override
    public Cargo findById(UUID id) {
        Cargo cargo = null;
        try (Connection connection = ConnectorDB.getConnection(); PreparedStatement statement = connection.prepareStatement(CargoSQL.GET.QUERY)) {
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                cargo = new Cargo();
                cargo.setId(rs.getObject("id", UUID.class));
                cargo.setName(rs.getString("name"));
                cargo.setDescription(rs.getString("description"));
                cargo.setType(CargoType.valueOf(rs.getString("type")));
                cargo.setState(CargoState.valueOf(rs.getString("state")));
                cargo.setWeight(rs.getDouble("weight"));
                cargo.setVolume(rs.getDouble("volume"));
                cargo.setCreatedAt((LocalDateTime) rs.getObject("createAt"));
                cargo.setModifiedAt((LocalDateTime) rs.getObject("modifiedAt"));
                cargo.setOwner(personDAO.findById(rs.getObject("id", UUID.class)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectorDB.closeConnection();
        }
        return cargo;
    }

    @Override
    public Cargo update(Cargo cargo) {
        try (Connection connection = ConnectorDB.getConnection(); PreparedStatement statement = connection.prepareStatement(CargoSQL.UPDATE.QUERY)) {
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

            ResultSet rs = statement.getGeneratedKeys();

            Cargo fromBase = findById(cargo.getId());
            fromBase.setOwner(personDAO.update(cargo.getOwner()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectorDB.closeConnection();
        }
        return cargo;
    }

    @Override
    public List<Cargo> findAll() {
        List<Cargo> cargoList = null;
        try (Connection connection = ConnectorDB.getConnection(); PreparedStatement statement = connection.prepareStatement(CargoSQL.GET_ALL.QUERY)) {

            ResultSet rs = statement.executeQuery();

            cargoList = new ArrayList<>();

            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(rs.getObject("id", UUID.class));
                cargo.setName(rs.getString("name"));
                cargo.setDescription(rs.getString("description"));
                cargo.setType(CargoType.valueOf(rs.getString("type")));
                cargo.setState(CargoState.valueOf(rs.getString("state")));
                cargo.setWeight(rs.getDouble("weight"));
                cargo.setVolume(rs.getDouble("volume"));
                cargo.setCreatedAt((LocalDateTime) rs.getObject("createAt"));
                cargo.setModifiedAt((LocalDateTime) rs.getObject("modifiedAt"));
                cargo.setOwner(personDAO.findById(rs.getObject("id", UUID.class)));

                cargoList.add(cargo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectorDB.closeConnection();
        }
        return cargoList;
    }


    @Override
    public void delete(UUID id) {
        if (findById(id) != null) {
            try (Connection connection = ConnectorDB.getConnection(); PreparedStatement statement = connection.prepareStatement(CargoSQL.DELETE.QUERY)) {
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
        try (Connection connection = ConnectorDB.getConnection(); PreparedStatement statement = connection.prepareStatement(CargoSQL.DELETE_ALL.QUERY)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectorDB.closeConnection();
        }
    }
}
