package service.impl;

import dao.CargoDAO;
import entity.Cargo;
import entity.dto.CargoDTO;
import service.CargoService;
import service.PersonService;

import java.util.List;
import java.util.UUID;

public class CargoServiceImpl implements CargoService {
    private final CargoDAO cargoDAO;
    private final PersonService personService;

    public CargoServiceImpl(CargoDAO cargoDAO, PersonService personService) {
        this.cargoDAO = cargoDAO;
        this.personService = personService;
    }

    @Override
    public CargoDTO create(Cargo cargo) {
        return null;
    }

    @Override
    public CargoDTO read(UUID id) {
        return null;
    }

    @Override
    public List<CargoDTO> getAll() {
        return null;
    }

    @Override
    public CargoDTO update(Cargo cargo) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void deleteAll() {

    }
}
