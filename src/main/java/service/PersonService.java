package service;

import dao.PersonDAO;
import dao.impl.PersonDAOImpl;
import mapper.PersonMapper;
import model.dto.PersonDto;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PersonService {
    private final PersonDAO personDAO;

    public PersonService() {
        this.personDAO = new PersonDAOImpl();
    }

    public PersonDto create(PersonDto personDto) {
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
        return PersonMapper.toDto(personDAO.update(PersonMapper.toEntity(person)));
    }

    public boolean delete(UUID id) {
        personDAO.delete(id);
        return false;
    }

    public void deleteAll() {
        personDAO.deleteAll();
    }
}
