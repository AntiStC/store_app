package dao.impl;

import dao.PersonDAO;
import exception.EntityNotCreateException;
import model.entity.Person;
import model.entity.PersonDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PersonDAOImplTest {

    private static PersonDAO personDAO;

    private static Person person;

    @BeforeAll
    static void setUp() throws EntityNotCreateException {
        personDAO = new PersonDAOImpl();

        PersonDetails personDetails = new PersonDetails.Builder()
                .setFirstName("firstName")
                .setLastName("lastName")
                .setPassportNum(12345)
                .setAddress("address")
                .build();


        person = new Person();
        person.setId(null);
        person.setLogin("testLogin");
        person.setPassword("testPassword");
        person.setDetails(personDetails);
        person = personDAO.create(person);
    }

    @Test
    void create() throws EntityNotCreateException {
        Assertions.assertNotNull(person.getId());
        Assertions.assertEquals("testLogin", person);
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