package dao.impl;

import dao.CargoDAO;
import dao.PersonDAO;
import dao.PersonDetailDAO;
import exception.EntityNotCreateException;
import model.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class PersonDAOImplTest {

    private static PersonDAO personDAO;

    private static Person person;

    @BeforeAll
    static void setUp() {
        PersonDAO personDAO = new PersonDAOImpl();
        PersonDetailDAO personDetailDAO = new PersonDetailDAOImpl();
        CargoDAO cargoDAO = new CargoDAOImpl();

        personDAO = new PersonDAOImpl();

        PersonDetails personDetails = new PersonDetails.Builder()
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


        Cargo cargo2 = new Cargo.Builder()
                .setName("secondCargo")
                .setDescription("desc for second")
                .setType(CargoType.SQUARE)
                .setState(CargoState.NOT_READY)
                .setWeight(2.7)
                .setVolume(1.2)
                .build();

        person = new Person();
        person.setId(null);
        person.setLogin("testLogin");
        person.setPassword("testPassword");
    }

    @Test
    void create() throws EntityNotCreateException {
        Assertions.assertEquals(personDAO.create(person), "firstName");
    }

    @Test
    void findAll() {

    }

    @Test
    void delete() {
    }

    @Test
    void deleteAll() {
    }


    @Test
    void findById() {
    }

    @Test
    void update() {
    }
}