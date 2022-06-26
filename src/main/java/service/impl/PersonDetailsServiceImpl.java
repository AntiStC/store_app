package service.impl;

import dao.PersonDetailsDAO;
import model.dto.PersonDetailsDto;
import mapper.CustomMapper;
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
        return CustomMapper.toDTO(personDetailsDAO.create(CustomMapper.toEntity(personDetailsDto)));
    }

    @Override
    public PersonDetailsDto read(UUID id) {
        return CustomMapper.toDTO(personDetailsDAO.findById(id));
    }

    @Override
    public List<PersonDetailsDto> getAll() {
        return personDetailsDAO.findAll().stream()
                .map(CustomMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDetailsDto update(PersonDetailsDto personDetailsDto) {
        return CustomMapper.toDTO(personDetailsDAO.update(CustomMapper.toEntity(personDetailsDto)));
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
