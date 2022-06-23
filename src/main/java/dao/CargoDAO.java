package dao;

import java.util.List;
//can use: public interface CargoDAO extends DAOService<Cargo, UUID>
public interface CargoDAO <UUID, Cargo> extends DAOService<Cargo>{
    List<Cargo> findByAll();

    Cargo findEntityById(UUID id);

    boolean delete (Cargo cargo);

    Cargo create (Cargo cargo);

    Cargo update (Cargo cargo);
}
