package service;

import dao.PersonDetailDAO;
import mapper.PersonDetailsMapper;
import model.dto.PersonDetailsDto;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PersonDetailsService {
    private final PersonDetailDAO personDetailDAO;

    public PersonDetailsService(PersonDetailDAO personDetailDAO) {
        this.personDetailDAO = personDetailDAO;
    }


    public PersonDetailsDto create(PersonDetailsDto personDetailsDto) {
        return PersonDetailsMapper.toDto(personDetailDAO.create(PersonDetailsMapper.toEntity(personDetailsDto)));
    }


    public PersonDetailsDto read(UUID id) {
        return PersonDetailsMapper.toDto(personDetailDAO.findById(id));
    }


    public List<PersonDetailsDto> getAll() {
        return personDetailDAO.findAll().stream()
                .map(PersonDetailsMapper::toDto)
                .collect(Collectors.toList());
    }

    public PersonDetailsDto update(PersonDetailsDto personDetailsDto) {
        return PersonDetailsMapper.toDto(personDetailDAO.update(PersonDetailsMapper.toEntity(personDetailsDto)));
    }

    public void delete(UUID id) {
        personDetailDAO.delete(id);
    }

    public void deleteAll() {
        personDetailDAO.deleteAll();
    }
}
