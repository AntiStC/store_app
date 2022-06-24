CREATE TABLE IF NOT EXISTS cargos
(
    id           UUID PRIMARY KEY,
    name         VARCHAR NOT NULL,
    description  VARCHAR NOT NULL,
    type         VARCHAR NOT NULL,
    state        VARCHAR NOT NULL,
    weight       DOUBLE PRECISION NOT NULL,
    volume       DOUBLE PRECISION NOT NULL,
    createAt     DATE,
    modifiedAt   DATE,
    owner        UUID NOT NULL,
    UNIQUE (owner),
    FOREIGN KEY (owner) REFERENCES persons (id)
);

CREATE TABLE IF NOT EXISTS persons
(
    id       UUID PRIMARY KEY,
    details  UUID NOT NULL,
    cargos   UUID NOT NULL,
    UNIQUE (details),
    UNIQUE (cargos),
    FOREIGN KEY (details) REFERENCES person_details (id),
    FOREIGN KEY (cargos) REFERENCES cargos(id)
);

CREATE TABLE IF NOT EXISTS person_details
(
    id          UUID PRIMARY KEY,
    first_name  VARCHAR NOT NULL,
    last_name   VARCHAR NOT NULL,
    passportNum BIGINT NOT NULL,
    address     VARCHAR NOT NULL,
    UNIQUE (passportNum)
);
