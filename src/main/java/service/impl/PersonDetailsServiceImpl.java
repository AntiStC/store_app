package service.impl;

import dao.PersonDetailsDAO;
import entity.PersonDetails;
import entity.dto.PersonDetailsDTO;
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
    public PersonDetailsDTO create(PersonDetails personDetails) {
        return CustomMapper.toDTO(personDetailsDAO.create(personDetails));
    }

    @Override
    public PersonDetailsDTO read(UUID id) {
        return CustomMapper.toDTO(personDetailsDAO.findById(id));
    }

    @Override
    public List<PersonDetailsDTO> getAll() {
        return personDetailsDAO.findAll().stream()
                .map(CustomMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDetailsDTO update(PersonDetails personDetails) {
        return CustomMapper.toDTO(personDetailsDAO.update(personDetails));
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
