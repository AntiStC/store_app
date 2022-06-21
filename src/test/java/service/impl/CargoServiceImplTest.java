package service.impl;


import org.junit.Before;
import org.junit.Test;
import service.CargoService;
import service.PersonDetailsService;
import service.PersonService;

class CargoServiceImplTest {
    private CargoService cargoService;
    private PersonService personService;
    private PersonDetailsService personDetailsService;

    @Before
    public void setUp(){
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