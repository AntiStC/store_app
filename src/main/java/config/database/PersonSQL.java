package config.database;

import entity.Person;

public enum PersonSQL {
    GET("SELECT * FROM persons WHERE id = (?)"),
    GET_ALL("SELECT * FROM persons"),
    INSERT("INSERT INTO persons(id, details, cargos)" +
            " VALUES(uuid_generate_v4(),(?),(?),(?)) RETURNING id"),
    DELETE("DELETE FROM persons WHERE id = (?)"),
    DELETE_ALL("TRUNCATE person CASCADE;"),
    UPDATE("UPDATE persons SET details = (?), cargos = (?)" +
            " WHERE id = (?) RETURNING id");

    public final String QUERY;

    String getQUERY() {
        return QUERY;
    }

    PersonSQL(String QUERY) {
        this.QUERY = QUERY;
    }
}