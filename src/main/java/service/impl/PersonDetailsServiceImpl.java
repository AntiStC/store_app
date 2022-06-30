package service.impl;

import dao.PersonDetailsDAO;
import mapper.PersonDetailsMapper;
import model.dto.PersonDetailsDto;
import service.PersonDetailsService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PersonDetailsServiceImpl implements PersonDetailsService {
    private final PersonDetailsDAO personDetailsDAO;

    public PersonDetailsServiceImpl(PersonDetailsDAO personDetailsDAO) {
        this.personDetailsDAO = personDetailsDAO;
    }

    @Override
    public PersonDetailsDto create(PersonDetailsDto personDetailsDto) {
        return PersonDetailsMapper.toDTO(personDetailsDAO.create(PersonDetailsMapper.toEntity(personDetailsDto)));
    }

    @Override
    public PersonDetailsDto read(UUID id) {
        return PersonDetailsMapper.toDTO(personDetailsDAO.findById(id));
    }

    @Override
    public List<PersonDetailsDto> getAll() {
        return personDetailsDAO.findAll().stream()
                .map(PersonDetailsMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDetailsDto update(PersonDetailsDto personDetailsDto) {
        return PersonDetailsMapper.toDTO(personDetailsDAO.update(PersonDetailsMapper.toEntity(personDetailsDto)));
    }

    @Override
    public void delete(UUID id) {
        personDetailsDAO.delete(id);
    }

    @Override
    public void deleteAll() {
        personDetailsDAO.deleteAll();
    }
}
