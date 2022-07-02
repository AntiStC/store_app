package service.impl;

import dao.CargoDAO;
import dao.PersonDAO;
import dao.PersonDetailDAO;
import dao.impl.CargoDAOImpl;
import dao.impl.PersonDAOImpl;
import dao.impl.PersonDetailDAOImpl;
import model.dto.CargoDto;
import model.dto.PersonDetailsDto;
import model.dto.PersonDto;
import model.entity.enums.CargoState;
import model.entity.enums.CargoType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.CargoService;
import service.PersonDetailsService;
import service.PersonService;

import java.util.Arrays;
import java.util.UUID;

class PersonServiceImplTest {

    private static PersonDAO personDAO;
    private static PersonDetailDAO personDetailDAO;

    private static CargoDAO cargoDAO;

    private static PersonService personService;
    private static PersonDetailsService personDetailsService;

    private static CargoService cargoService;

    private static PersonDto person;

    @BeforeAll
    static void setUp() {
        personDAO = new PersonDAOImpl();
        personDetailDAO = new PersonDetailDAOImpl();
        cargoDAO = new CargoDAOImpl();

        personDetailsService = new PersonDetailsServiceImpl(personDetailDAO);
        cargoService = new CargoServiceImpl(cargoDAO);
        personService = new PersonServiceImpl(personDAO, personDetailsService, cargoService);

        PersonDetailsDto personDetailsDto = new PersonDetailsDto.Builder()
                .setFirstName("firstName")
                .setLastName("lastName")
                .setPassportNum(12345)
                .setAddress("address")
                .build();


        CargoDto cargo1 = new CargoDto.Builder()
                .setName("firstCargo")
                .setDescription("desc for first")
                .setType(CargoType.ROUND)
                .setState(CargoState.READY)
                .setWeight(0.7)
                .setVolume(11.2)
                .build();


        CargoDto cargo2 = new CargoDto.Builder()
                .setName("secondCargo")
                .setDescription("desc for second")
                .setType(CargoType.SQUARE)
                .setState(CargoState.NOT_READY)
                .setWeight(2.7)
                .setVolume(1.2)
                .build();

        person = new PersonDto.Builder()
                .setId(null)
                .setLogin("testLogin")
                .setPassword("testPassword")
                .setDetails(personDetailsDto)
                .setCargoList(Arrays.asList(cargo1, cargo2))
                .build();

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