package service.impl;

import dao.PersonDetailsDAO;
import entity.PersonDetails;
import entity.dto.PersonDetailsDTO;
import service.PersonDetailsService;

import java.util.List;
import java.util.UUID;

public class PersonDetailsServiceImpl implements PersonDetailsService {
    //TODO use interface instead
    private final PersonDetailsDAO personDetailsDAO;

    public PersonDetailsServiceImpl(PersonDetailsDAO personDetailsDAO) {
        this.personDetailsDAO = personDetailsDAO;
    }

    @Override
    public PersonDetailsDTO create(PersonDetails personDetails) {
        return null;
    }

    @Override
    public PersonDetailsDTO read(UUID id) {
        return null;
    }

    @Override
    public List<PersonDetailsDTO> getAll() {
        return null;
    }

    @Override
    public PersonDetailsDTO update(PersonDetails personDetails) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void deleteAll() {

    }
}
