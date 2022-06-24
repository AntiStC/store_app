package config.database;

public enum PersonDetailsSQL {
    GET("SELECT * FROM person_details WHERE id = (?)"),
    GET_ALL("SELECT * FROM person_details"),
    INSERT("INSERT INTO person_details(id, first_name, last_name, passportNum, address)" +
            " VALUES(uuid_generate_v4(),(?),(?),(?)) RETURNING id"),
    DELETE("DELETE FROM person_details WHERE id = (?)"),
    DELETE_ALL("TRUNCATE person_details CASCADE;"),
    UPDATE("UPDATE person_details SET first_name = (?), last_name = (?) " +
            "passportNum = (?), address = (?) WHERE id = (?) RETURNING id");

    public final String QUERY;

    String getQUERY() {
        return QUERY;
    }

    PersonDetailsSQL(String QUERY) {
        this.QUERY = QUERY;
    }
}