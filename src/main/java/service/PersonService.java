package service;

import dao.PersonDAO;
import mapper.PersonMapper;
import model.dto.PersonDto;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PersonService {
    private final PersonDAO personDAO;
    private final PersonDetailsService personDetailsService;

    private final CargoService cargoService;

    public PersonService(PersonDAO personDAO, PersonDetailsService personDetailsService, CargoService cargoService) {
        this.personDAO = personDAO;
        this.personDetailsService = personDetailsService;
        this.cargoService = cargoService;
    }

    public PersonDto create(PersonDto personDto) {
        personDetailsService.create(personDto.getDetails());
        personDto.getCargoList().forEach(cargoService::create);

        return PersonMapper.toDto(personDAO.create(PersonMapper.toEntity(personDto)));
    }

    public PersonDto read(UUID id) {
        return PersonMapper.toDto(personDAO.findById(id));
    }

    public List<PersonDto> getAll() {
        return personDAO.findAll().stream()
                .map(PersonMapper::toDto)
                .collect(Collectors.toList());
    }

    public PersonDto update(PersonDto person) {
        personDetailsService.update(person.getDetails());
        return PersonMapper.toDto(personDAO.update(PersonMapper.toEntity(person)));
    }

    public void delete(UUID id) {
        personDAO.delete(id);
    }

    public void deleteAll() {
        personDAO.deleteAll();
    }
}
