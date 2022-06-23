package dao;

import java.util.List;

public interface PersonDetailsDAO<UUID, PersonDetails> extends DAOService<PersonDetails>{

    List<PersonDetails> findByAll();

    PersonDetails findEntityById(UUID id);

    boolean delete (PersonDetails personDetails);

    PersonDetails create (PersonDetails personDetails);

    PersonDetails update (PersonDetails personDetails);
}
