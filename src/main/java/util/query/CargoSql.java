package util.query;

public class CargoSql {

    private CargoSql() {
        throw new AssertionError(String.format("Class %s cannot be instantiated", this.getClass().getSimpleName()));
    }

    public static final String SQL_QUERY_CARGO_GET =
            """
                    SELECT cargo_id,
                           name,
                           description,
                           type,
                           state,
                           weight,
                           volume,
                           create_at,
                           modified_at,
                           person_fk
                    FROM cargo
                           INNER JOIN cargo_list cl on cargo.cargo_id = cl.cargo_fk
                    WHERE cargo_id = (?)
                    """;
    public static final String SQL_QUERY_CARGO_GET_ALL =
            """
                    SELECT cargo_id,
                           name,
                           description,
                           type,
                           state,
                           weight,
                           volume,
                           create_at,
                           modified_at,
                           person_fk
                    FROM cargo
                           INNER JOIN cargo_list cl on cargo.cargo_id = cl.cargo_fk
                    """;
    public static final String SQL_QUERY_CARGO_INSERT =
            """
                    DO
                    $$
                        DECLARE
                            tableId uuid;
                        BEGIN
                            INSERT INTO public.cargo(cargo_id,
                                                    name,
                                                    description,
                                                    type,
                                                    state,
                                                    weight,
                                                    volume,
                                                    create_at,
                                                    modified_at,
                                                    person_fk)
                            VALUES (uuid_generate_v4(), (?), (?), (?), (?), (?), (?), (?), (?), (?))
                            RETURNING person_fk INTO tableId;
                            INSERT INTO public.cargo_list(person_list_fk) VALUES (tableId);
                            COMMIT;
                        END
                    $$;
                    """;
    public static final String SQL_QUERY_CARGO_DELETE =
            """
                    DELETE 
                    FROM cargo 
                    WHERE cargo_id = (?)
                    """;
    public static final String SQL_QUERY_CARGO_DELETE_ALL =
            """
                    TRUNCATE cargo CASCADE
                    """;
    public static final String SQL_QUERY_CARGO_UPDATE =
            """
                    UPDATE cargo
                    SET name        = (?),
                        description = (?),
                        type        = (?),
                        state       = (?),
                        weight      = (?),
                        volume      = (?),
                        create_at   = (?),
                        modified_at = (?),
                        person_fk   = (?)
                    WHERE cargo_id  = (?)
                    RETURNING cargo_id
                    """;


}
