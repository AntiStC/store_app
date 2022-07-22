package util.query;

public class PersonSql {

    private PersonSql() {
        throw new AssertionError(String.format("Class %s cannot be instantiated", this.getClass().getSimpleName()));
    }

    public static final String SQL_QUERY_PERSON_GET =
            """
                    SELECT id,
                           login,
                           password
                    FROM person
                            INNER JOIN person_detail pd
                                       ON person.person_detail_fk = pd.person_detail_id
                            INNER JOIN cargo_list cl
                                       ON person.person_id = cl.person_list_fk
                    WHERE id = (?)
                    """;
    public static final String SQL_QUERY_PERSON_GET_ALL =
            """
                   SELECT id,
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
                    id,
                    login,
                    password)
                    VALUES(uuid_generate_v4(),(?),(?))
                    RETURNING id
                    """;
    public static final String SQL_QUERY_PERSON_DELETE =
            """
                    DELETE FROM person
                    WHERE id = (?)
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
                    WHERE id = (?)
                    RETURNING id
                    """;
}
