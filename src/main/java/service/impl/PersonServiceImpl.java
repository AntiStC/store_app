package service.impl;

import dao.PersonDAO;
import entity.Person;
import entity.dto.PersonDTO;
import mapper.CustomMapper;
import service.PersonDetailsService;
import service.PersonService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PersonServiceImpl implements PersonService {
    //TODO use interface instead
    private final PersonDAO personDAO;
    private final PersonDetailsService personDetailsService;

    public PersonServiceImpl(PersonDAO personDAO, PersonDetailsService personDetailsService) {
        this.personDAO = personDAO;
        this.personDetailsService = personDetailsService;
    }

    @Override
    public PersonDTO create(Person person) {
        personDAO.create(person);
        personDetailsService.create(person.getDetails());
        return CustomMapper.toDTO(person);
    }

    @Override
    public PersonDTO read(UUID id) {
        return CustomMapper.toDTO(personDAO.findEntityById(id));
    }

    @Override
    public List<PersonDTO> getAll() {
        return personDAO.findAll().stream()
                .map(CustomMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO update(Person person) {
        personDetailsService.update(person.getDetails());
        return CustomMapper.toDTO(personDAO.update(person));
    }

    @Override
    public void delete(UUID id) {
        personDAO.delete(id);
    }

    @Override
    public void delete(Person person) {
        personDAO.delete(person);
    }
}
