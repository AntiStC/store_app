package service.impl;

import dao.PersonDetailDAO;
import mapper.PersonDetailsMapper;
import model.dto.PersonDetailsDto;
import service.PersonDetailsService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PersonDetailsServiceImpl implements PersonDetailsService {
    private final PersonDetailDAO personDetailDAO;

    public PersonDetailsServiceImpl(PersonDetailDAO personDetailDAO) {
        this.personDetailDAO = personDetailDAO;
    }

    @Override
    public PersonDetailsDto create(PersonDetailsDto personDetailsDto) {
        return PersonDetailsMapper.toDto(personDetailDAO.create(PersonDetailsMapper.toEntity(personDetailsDto)));
    }

    @Override
    public PersonDetailsDto read(UUID id) {
        return PersonDetailsMapper.toDto(personDetailDAO.findById(id));
    }

    @Override
    public List<PersonDetailsDto> getAll() {
        return personDetailDAO.findAll().stream()
                .map(PersonDetailsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDetailsDto update(PersonDetailsDto personDetailsDto) {
        return PersonDetailsMapper.toDto(personDetailDAO.update(PersonDetailsMapper.toEntity(personDetailsDto)));
    }

    @Override
    public void delete(UUID id) {
        personDetailDAO.delete(id);
    }

    @Override
    public void deleteAll() {
        personDetailDAO.deleteAll();
    }
}
