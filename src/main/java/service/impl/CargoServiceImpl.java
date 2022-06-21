package service.impl;

import entity.Cargo;
import entity.dto.CargoDTO;
import service.CargoService;
import service.PersonService;

public class CargoServiceImpl implements CargoService {
    private final PersonService personService;

    public CargoServiceImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public CargoDTO create(Cargo cargo) {
        return null;
    }

    @Override
    public CargoDTO read(Cargo cargo) {
        return null;
    }

    @Override
    public CargoDTO update(Cargo cargo) {
        return null;
    }

    @Override
    public boolean delete(Cargo cargo) {
        return false;
    }
}
