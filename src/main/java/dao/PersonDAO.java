package dao;

import model.entity.Person;

import java.util.List;
import java.util.UUID;


public interface PersonDAO extends DAOService<Person, UUID>{

    @Override
    default List<Person> findAll() {
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
