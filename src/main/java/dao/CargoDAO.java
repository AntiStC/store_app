package dao;

import model.entity.Cargo;

import java.util.List;
import java.util.UUID;


public interface CargoDAO extends DAOService<Cargo, UUID> {

    @Override
    default Cargo create(Cargo cargo) {
        return null;
    }

    @Override
    default Cargo findById(UUID uuid) {
        return null;
    }

    @Override
    default Cargo update(Cargo cargo) {
        return null;
    }

    @Override
    default List<Cargo> findAll() {
        return null;
    }

    @Override
    default boolean delete(UUID uuid) {
        return false;
    }

    @Override
    default boolean deleteAll() {
        return false;
    }
}