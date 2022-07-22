package dao;

import model.entity.PersonDetails;

import java.util.List;
import java.util.UUID;

public interface PersonDetailDAO extends DAOService<PersonDetails, UUID>{

    @Override
    default List<PersonDetails> findAll() {
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
