package dao;

import model.entity.Cargo;

import java.util.List;
import java.util.UUID;


public interface CargoDAO extends DAOService<Cargo, UUID> {
    public List<Cargo> findByPersonId(UUID id);

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