package service.impl;

import dao.PersonDAO;
import mapper.PersonMapper;
import model.dto.PersonDto;
import service.CargoService;
import service.PersonDetailsService;
import service.PersonService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PersonServiceImpl implements PersonService {
    private final PersonDAO personDAO;
    private final PersonDetailsService personDetailsService;

    private final CargoService cargoService;

    public PersonServiceImpl(PersonDAO personDAO, PersonDetailsService personDetailsService, CargoService cargoService) {
        this.personDAO = personDAO;
        this.personDetailsService = personDetailsService;
        this.cargoService = cargoService;
    }

    @Override
    public PersonDto create(PersonDto personDto) {
        personDetailsService.create(personDto.getDetails());
        personDto.getCargoList().forEach(cargoService::create);

        return PersonMapper.toDto(personDAO.create(PersonMapper.toEntity(personDto)));
    }

    @Override
    public PersonDto read(UUID id) {
        return PersonMapper.toDto(personDAO.findById(id));
    }

    @Override
    public List<PersonDto> getAll() {
        return personDAO.findAll().stream()
                .map(PersonMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto update(PersonDto person) {
        personDetailsService.update(person.getDetails());
        return PersonMapper.toDto(personDAO.update(PersonMapper.toEntity(person)));
    }

    @Override
    public void delete(UUID id) {
        personDAO.delete(id);
    }

    @Override
    public void deleteAll() {
        personDAO.deleteAll();
    }
}
