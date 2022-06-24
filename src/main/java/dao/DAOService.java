package dao;

import java.util.List;

public interface DAOService <Entity, ID> {

    Entity create(Entity entity);

    Entity findById(ID id);

    Entity update(Entity entity);

    List<Entity> findAll();

    void delete(ID id);

    void deleteAll();
}
