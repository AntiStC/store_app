package service.impl;

import dao.CargoDAO;
import mapper.CargoMapper;
import model.dto.CargoDto;
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
        return CargoMapper.toDTO(cargoDAO.create(CargoMapper.toEntity(cargoDto)));
    }

    @Override
    public CargoDto read(UUID id) {
        return CargoMapper.toDTO(cargoDAO.findById(id));
    }

    @Override
    public List<CargoDto> getAll() {
        return cargoDAO.findAll().stream()
                .map(CargoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CargoDto update(CargoDto cargoDto) {
        return CargoMapper.toDTO(cargoDAO.update(CargoMapper.toEntity(cargoDto)));
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
