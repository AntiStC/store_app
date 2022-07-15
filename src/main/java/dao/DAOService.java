package dao;

import java.util.List;

public interface DAOService <Entity, ID> {

    Entity create(Entity entity);

    Entity findById(ID id);

    Entity update(Entity entity);

    List<Entity> findAll();

    boolean delete(ID id);

    boolean deleteAll();
}
