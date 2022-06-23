package dao;

import java.util.UUID;

public interface DAOService <T> {

    T create(T entity);

    T update(T entity);

    boolean delete(T entity);
}
