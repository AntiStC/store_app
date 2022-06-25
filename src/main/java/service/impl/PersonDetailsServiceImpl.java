package service.impl;

import dao.PersonDetailsDAO;
import entity.PersonDetails;
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
    public dto.PersonDetailsDto create(PersonDetails personDetailsDto) {
        return CustomMapper.toDTO(personDetailsDAO.create(personDetailsDto));
    }

    @Override
    public dto.PersonDetailsDto read(UUID id) {
        return CustomMapper.toDTO(personDetailsDAO.findById(id));
    }

    @Override
    public List<dto.PersonDetailsDto> getAll() {
        return personDetailsDAO.findAll().stream()
                .map(CustomMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public dto.PersonDetailsDto update(PersonDetails personDetailsDto) {
        return CustomMapper.toDTO(personDetailsDAO.update(personDetailsDto));
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
