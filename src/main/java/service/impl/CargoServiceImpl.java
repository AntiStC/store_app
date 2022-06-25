package service.impl;

import dao.CargoDAO;
import entity.Cargo;
import dto.CargoDto;
import mapper.CustomMapper;
import service.CargoService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CargoServiceImpl implements CargoService {
    private final CargoDAO cargoDAO;

    public CargoServiceImpl(CargoDAO cargoDAO) {
        this.cargoDAO = cargoDAO;
    }

    @Override
    public CargoDto create(Cargo cargo) {
        return CustomMapper.toDTO(cargoDAO.create(cargo));
    }

    @Override
    public CargoDto read(UUID id) {
        return CustomMapper.toDTO(cargoDAO.findById(id));
    }

    @Override
    public List<CargoDto> getAll() {
        return cargoDAO.findAll().stream()
                .map(CustomMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CargoDto update(Cargo cargo) {
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
