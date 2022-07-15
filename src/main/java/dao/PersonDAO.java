package dao;

import model.entity.Person;

import java.util.List;
import java.util.UUID;


public interface PersonDAO extends DAOService<Person, UUID>{

    @Override
    default Person create(Person person) {
        return null;
    }

    @Override
    default Person findById(UUID uuid) {
        return null;
    }

    @Override
    default Person update(Person person) {
        return null;
    }

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
