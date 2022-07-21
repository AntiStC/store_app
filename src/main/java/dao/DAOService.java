package dao;

import exception.EntityNotCreateException;
import exception.EntityNotFoundException;

import java.util.List;

public interface DAOService <Entity, ID> {

    Entity create(Entity entity) throws EntityNotCreateException;

    Entity findById(ID id) throws EntityNotFoundException;

    Entity update(Entity entity) throws EntityNotCreateException;

    List<Entity> findAll();

    boolean delete(ID id) throws EntityNotFoundException;

    boolean deleteAll() throws EntityNotFoundException;
}
