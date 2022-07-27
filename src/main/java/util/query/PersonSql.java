package util.query;

public class PersonSql {

    private PersonSql() {
        throw new AssertionError(String.format("Class %s cannot be instantiated", this.getClass().getSimpleName()));
    }

    public static final String SQL_QUERY_PERSON_GET =
            """
                    SELECT person_id,
                           login,
                           password
                    FROM person
                            INNER JOIN person_detail pd
                                       ON person.person_detail_fk = pd.person_detail_id
                            INNER JOIN cargo_list cl
                                       ON person.person_id = cl.person_list_fk
                    WHERE person_id = (?)
                    """;
    public static final String SQL_QUERY_PERSON_GET_ALL =
            """
                   SELECT person_id,
                           login,
                           password
                    FROM person
                            INNER JOIN person_detail pd
                                       ON person.person_detail_fk = pd.person_detail_id
                            INNER JOIN cargo_list cl
                                       ON person.person_id = cl.person_list_fk
                    """;
    public static final String SQL_QUERY_PERSON_INSERT =
            """
                    INSERT INTO person(
                    person_id,
                    login,
                    password)
                    VALUES(uuid_generate_v4(),(?),(?))
                    RETURNING person_id
                    """;
    public static final String SQL_QUERY_PERSON_DELETE =
            """
                    DELETE FROM person
                    WHERE person_id = (?)
                    """;
    public static final String SQL_QUERY_PERSON_DELETE_ALL =
            """
                    TRUNCATE person CASCADE
                    """;
    public static final String SQL_QUERY_PERSON_UPDATE =
            """
                    UPDATE person
                    SET login = (?),
                    password = (?)
                    WHERE person_id = (?)
                    RETURNING person_id
                    """;
}
