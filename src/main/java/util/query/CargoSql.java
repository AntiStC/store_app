package util.query;

public class CargoSql {

    private CargoSql() {
        throw new AssertionError(String.format("Class %s cannot be instantiated", this.getClass().getSimpleName()));
    }
    public static final String SQL_QUERY_CARGO_GET_BY_PERSON_ID =
            """
                    SELECT id,
                    name,
                    description,
                    type,
                    state,
                    weight,
                    volume,
                    create_at,
                    modified_at,
                    person_id FROM
                    cargo WHERE person_id = (?)
                    """;

    public static final String SQL_QUERY_CARGO_GET =
            """
                    SELECT id,
                    name,
                    description,
                    type,
                    state,
                    weight,
                    volume,
                    create_at,
                    modified_at,
                    person_id FROM
                    cargo WHERE id = (?)
                    """;
    public static final String SQL_QUERY_CARGO_GET_ALL =
            """
                    SELECT id,
                    name,
                    description,
                    type,
                    state,
                    weight,
                    volume,
                    create_at,
                    modified_at,
                    person_id FROM
                    cargo
                    """;
    public static final String SQL_QUERY_CARGO_INSERT =
            """
                    INSERT INTO cargo(
                    id,
                    name,
                    description,
                    type,
                    state,
                    weight,
                    volume,
                    person_id)
                    VALUES(uuid_generate_v4(),(?),(?),(?),(?),(?),(?),(?))
                    RETURNING id
                    """;
    public static final String SQL_QUERY_CARGO_DELETE =
            """
                    DELETE FROM
                    cargo WHERE id = (?)
                    """;
    public static final String SQL_QUERY_CARGO_DELETE_ALL =
            """
                    TRUNCATE cargo CASCADE
                    """;
    public static final String SQL_QUERY_CARGO_UPDATE =
            """
                    UPDATE cargo SET
                    name = (?),
                    description = (?),
                    type = (?),
                    state = (?),
                    weight = (?),
                    volume = (?),
                    create_at = (?),
                    modified_at = (?),
                    person_fk = (?)
                    WHERE id = (?)
                    RETURNING id
                    """;


}
