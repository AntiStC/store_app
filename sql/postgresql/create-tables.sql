CREATE TABLE IF NOT EXISTS cargos
(
    id           UUID PRIMARY KEY,
    name         VARCHAR NOT NULL,
    description  VARCHAR NOT NULL,
    type         VARCHAR NOT NULL,
    state        VARCHAR NOT NULL,
    weight       DOUBLE PRECISION NOT NULL,
    volume       DOUBLE PRECISION NOT NULL,
    create_at     DATE,
    modified_at   DATE,
    owner_id        UUID NOT NULL,
    UNIQUE (owner_id)
);

CREATE TABLE IF NOT EXISTS persons
(
    id       UUID PRIMARY KEY,
    details_id  UUID NOT NULL,
    cargos_id   UUID NOT NULL,
    UNIQUE (details_id),
    UNIQUE (cargos_id)
);

CREATE TABLE IF NOT EXISTS person_details
(
    id          UUID PRIMARY KEY,
    first_name  VARCHAR NOT NULL,
    last_name   VARCHAR NOT NULL,
    passport_num BIGINT NOT NULL,
    address     VARCHAR NOT NULL,
    UNIQUE (passport_num)
);
