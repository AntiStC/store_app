package service.impl;

import dao.CargoDAO;
import dao.PersonDAO;
import dao.PersonDetailsDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.CargoService;
import service.PersonDetailsService;
import service.PersonService;

class CargoServiceImplTest {
    private static CargoService cargoService;
    private static PersonDAO personDAO;
    private static CargoDAO cargoDAO;
    private static PersonDetailsDAO personDetailsDAO;
    private static PersonService personService;
    private static PersonDetailsService personDetailsService;

    @BeforeAll
    static void setUp() {
        personDetailsService = new PersonDetailsServiceImpl(personDetailsDAO);
        personService = new PersonServiceImpl(personDAO, personDetailsService);
        cargoService = new CargoServiceImpl(cargoDAO, personService);
    }

    @Test
    void create() {
    }

    @Test
    void read() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}