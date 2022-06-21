package service.impl;

import entity.Person;
import entity.dto.PersonDTO;
import service.PersonDetailsService;
import service.PersonService;

public class PersonServiceImpl implements PersonService {
    private final PersonDetailsService personDetailsService;

    public PersonServiceImpl(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public PersonDTO create(Person person) {
        return null;
    }

    @Override
    public PersonDTO read(Person person) {
        return null;
    }

    @Override
    public PersonDTO update(Person person) {
        return null;
    }

    @Override
    public boolean delete(Person person) {
        return false;
    }
}
