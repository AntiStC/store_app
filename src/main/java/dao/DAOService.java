package dao;

import java.util.List;
import java.util.UUID;
//can use: public interface DAOService<Entity, ID>
public interface DAOService <Entity, ID> {

    Entity create(Entity entity);

    Entity findById(ID id);

    Entity update(Entity entity);

    List<Entity> findAll();

    boolean delete(Entity entity);

    void deleteAll();
}
