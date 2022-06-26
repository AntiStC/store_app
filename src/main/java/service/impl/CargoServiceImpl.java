package service.impl;

import dao.CargoDAO;
import model.dto.CargoDto;
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
    public CargoDto create(CargoDto cargoDto) {
        return CustomMapper.toDTO(cargoDAO.create(CustomMapper.toEntity(cargoDto)));
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
    public CargoDto update(CargoDto cargoDto) {
        return CustomMapper.toDTO(cargoDAO.update(CustomMapper.toEntity(cargoDto)));
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
