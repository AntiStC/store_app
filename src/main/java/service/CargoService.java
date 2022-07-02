package service;

import dao.CargoDAO;
import mapper.CargoMapper;
import model.dto.CargoDto;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CargoService {
    private final CargoDAO cargoDAO;

    public CargoService(CargoDAO cargoDAO) {
        this.cargoDAO = cargoDAO;
    }

    public CargoDto create(CargoDto cargoDto) {
        return CargoMapper.toDto(cargoDAO.create(CargoMapper.toEntity(cargoDto)));
    }

    public CargoDto read(UUID id) {
        return CargoMapper.toDto(cargoDAO.findById(id));
    }


    public List<CargoDto> getAll() {
        return cargoDAO.findAll().stream()
                .map(CargoMapper::toDto)
                .collect(Collectors.toList());
    }


    public CargoDto update(CargoDto cargoDto) {
        return CargoMapper.toDto(cargoDAO.update(CargoMapper.toEntity(cargoDto)));
    }

    public void delete(UUID id) {
        cargoDAO.delete(id);
    }

    public void deleteAll() {
        cargoDAO.deleteAll();
    }
}
