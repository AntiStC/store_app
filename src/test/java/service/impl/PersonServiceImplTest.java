package service.impl;

import dao.CargoDAO;
import dao.PersonDAO;
import dao.PersonDetailsDAO;
import dao.impl.CargoDAOImpl;
import dao.impl.PersonDAOImpl;
import dao.impl.PersonDetailsDAOImpl;
import entity.Cargo;
import entity.Person;
import entity.PersonDetails;
import entity.enums.CargoState;
import entity.enums.CargoType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.CargoService;
import service.PersonDetailsService;
import service.PersonService;

import java.util.UUID;

class PersonServiceImplTest {

    private static PersonDAO personDAO;
    private static PersonDetailsDAO personDetailsDAO;

    private static CargoDAO cargoDAO;

    private static PersonService personService;
    private static PersonDetailsService personDetailsService;

    private static CargoService cargoService;

    private static Person person;

    @BeforeAll
    static void setUp() {
        personDAO = new PersonDAOImpl();
        personDetailsDAO = new PersonDetailsDAOImpl();
        cargoDAO = new CargoDAOImpl();

        personDetailsService = new PersonDetailsServiceImpl(personDetailsDAO);
        cargoService = new CargoServiceImpl(cargoDAO);
        personService = new PersonServiceImpl(personDAO, personDetailsService, cargoService);

        PersonDetails personDetailsDto = new PersonDetails.Builder()
                .setFirstName("firstName")
                .setLastName("lastName")
                .setPassportNum(12345)
                .setAddress("address")
                .build();


        Cargo cargo1 = new Cargo.Builder()
                .setName("firstCargo")
                .setDescription("desc for first")
                .setType(CargoType.ROUND)
                .setState(CargoState.READY)
                .setWeight(0.7)
                .setVolume(11.2)
                .build();


        Cargo cargo2 =  new Cargo.Builder()
                .setName("secondCargo")
                .setDescription("desc for second")
                .setType(CargoType.SQUARE)
                .setState(CargoState.NOT_READY)
                .setWeight(2.7)
                .setVolume(1.2)
                .build();

        person = new Person();
        person.setDetails(personDetailsDto);
        person.addCargo(cargo1);
        person.addCargo(cargo2);
    }

    @Test
    void create() {
        Assertions.assertEquals(personService.create(person).getDetails().getFirstName(), "firstName");
    }

    @Test
    void read() {
        UUID id = personService.create(person).getId();

        Assertions.assertEquals(personService.read(id).getDetails().getFirstName(), "firstName");
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteAll() {
    }
}