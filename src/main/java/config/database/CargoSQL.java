package config.database;

public enum CargoSQL {
    GET("SELECT * FROM cargos WHERE id = (?)"),
    GET_ALL("SELECT * FROM cargos"),
    INSERT("INSERT INTO cargos(id, name, description, type, state, weight," +
            " volume, createAt, modifiedAt, owner)" +
            " VALUES(uuid_generate_v4(),(?),(?),(?)) RETURNING id"),
    DELETE("DELETE FROM cargos WHERE id = (?)"),
    DELETE_ALL("TRUNCATE cargos CASCADE;"),
    UPDATE("UPDATE cargos SET name = (?), description = (?), type = (?)," +
            " state = (?), weight = (?), volume = (?), createAt = (?), modifiedAt = (?)," +
            " owner = (?) WHERE id = (?) RETURNING id");

    public final String QUERY;

    String getQUERY() {
        return QUERY;
    }

    CargoSQL(String QUERY) {
        this.QUERY = QUERY;
    }
}