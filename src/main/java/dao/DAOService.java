package dao;

import java.util.UUID;
//can use: public interface DAOService<Entity, ID>
public interface DAOService <T> {

    T create(T entity);

    T update(T entity);

    boolean delete(T entity);
}
