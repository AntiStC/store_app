package dao;

import java.util.List;

public interface CargoDAO <UUID, Cargo> extends DAOService<Cargo>{
    List<Cargo> findByAll();

    Cargo findEntityById(UUID id);

    boolean delete (Cargo cargo);

    Cargo create (Cargo cargo);

    Cargo update (Cargo cargo);
}
