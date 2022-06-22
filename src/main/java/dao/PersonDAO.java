package dao;

import config.database.PostgresConnUtils;
import entity.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersonDAO extends AbstractDAO<Number, Person> {
    public static final String SQL_SELECT_ALL_PERSON = "SELECT * FROM persons";
    public static final String SQL_SELECT_PERSON_ID = "SELECT * FROM persons WHERE id=?";

    @Override
    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = PostgresConnUtils.getPostgresConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_PERSON);
            while (rs.next()) {
                long id = rs.getLong(1);
                persons.add(new Person(id));
            }
        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return persons;
    }

    @Override
    public Person findEntityById(Number id) {
        Person person=null;
        try (Connection connection=PostgresConnUtils.getPostgresConnection();
             PreparedStatement statement=connection.prepareStatement(SQL_SELECT_PERSON_ID)){
            statement.setInt(1, (Integer) id);
            ResultSet rs= statement.executeQuery();
            if (rs.next()){
                person = new Person((Long) id);
            }
        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return person;
    }

    @Override
    public boolean delete(Number id) {
        return false;
    }

    @Override
    public Person findEntityById(UUID id) {
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }

    @Override
    public boolean delete(Person entity) {
        return false;
    }

    @Override
    public boolean create(Person entity) {
        return false;
    }

    @Override
    public Person update(Person entity) {
        return null;
    }
}
