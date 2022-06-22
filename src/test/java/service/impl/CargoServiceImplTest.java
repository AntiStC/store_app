package service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.CargoService;
import service.PersonDetailsService;
import service.PersonService;

class CargoServiceImplTest {
    private static CargoService cargoService;
    private static PersonService personService;
    private static PersonDetailsService personDetailsService;

    @BeforeAll
    static void setUp(){
        personDetailsService = new PersonDetailsServiceImpl();
        personService =new PersonServiceImpl(personDetailsService);
        cargoService = new CargoServiceImpl(personService);
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