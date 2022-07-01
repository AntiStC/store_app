package util.query;

public class PersonDetailSql {
    public static final String SQL_QUERY_PERSON_DETAIL_GET =
            """
                    SELECT id,
                    first_name,
                    last_name,
                    passport_num,
                    address
                    FROM person_detail
                    WHERE id = (?)
                    """;
    public static final String SQL_QUERY_PERSON_DETAIL_GET_ALL =
            """
                    SELECT id,
                    first_name,
                    last_name,
                    passport_num,
                    address
                    FROM person_detail
                    """;
    public static final String SQL_QUERY_PERSON_DETAIL_INSERT =
            """
                    INSERT INTO person_detail(
                    id,
                    first_name,
                    last_name,
                    passport_num,
                    address)
                    VALUES(uuid_generate_v4(),(?),(?),(?))
                    RETURNING id
                    """;
    public static final String SQL_QUERY_PERSON_DETAIL_DELETE =
            """
                    DELETE FROM
                    person_detail
                    WHERE id = (?)
                    """;
    public static final String SQL_QUERY_PERSON_DETAIL_DELETE_ALL =
            """
                    TRUNCATE person_detail CASCADE
                    """;
    public static final String SQL_QUERY_PERSON_DETAIL_UPDATE =
            """
                    UPDATE
                    person_detail
                    SET first_name = (?),
                    last_name = (?),
                    passport_num = (?),
                    address = (?)
                    WHERE id = (?)
                    RETURNING id
                    """;
}
