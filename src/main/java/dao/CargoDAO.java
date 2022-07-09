package dao;

import model.entity.Cargo;

import java.util.List;
import java.util.UUID;


public interface CargoDAO extends DAOService<Cargo, UUID> {
    public List<Cargo> findByPersonId(UUID id);

}