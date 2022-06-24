package service.impl;

import dao.CargoDAO;
import entity.Cargo;
import entity.dto.CargoDTO;
import mapper.CustomMapper;
import service.CargoService;
import service.PersonService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CargoServiceImpl implements CargoService {
    private final CargoDAO cargoDAO;

    public CargoServiceImpl(CargoDAO cargoDAO) {
        this.cargoDAO = cargoDAO;
    }

    @Override
    public CargoDTO create(Cargo cargo) {
        return CustomMapper.toDTO(cargoDAO.create(cargo));
    }

    @Override
    public CargoDTO read(UUID id) {
        return CustomMapper.toDTO(cargoDAO.findById(id));
    }

    @Override
    public List<CargoDTO> getAll() {
        return cargoDAO.findAll().stream()
                .map(CustomMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CargoDTO update(Cargo cargo) {
        return CustomMapper.toDTO(cargoDAO.update(cargo));
    }

    @Override
    public void delete(UUID id) {
        cargoDAO.delete(id);
    }

    @Override
    public void deleteAll() {
        cargoDAO.deleteAll();
    }
}
