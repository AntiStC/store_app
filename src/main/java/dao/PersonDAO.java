package dao;

import java.util.List;

public interface PersonDAO <UUID, Person> extends DAOService<Person>{

    List<Person> findByAll();

    Person findEntityById(UUID id);

    boolean delete (Person person);

    Person create (Person person);

    Person update (Person person);
}
