CREATE TABLE IF NOT EXISTS person
(
    id          UUID PRIMARY KEY,
    login       VARCHAR NOT NULL,
    password    VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS cargo
(
    id           UUID PRIMARY KEY,
    name         VARCHAR,
    description  VARCHAR,
    type         VARCHAR NOT NULL,
    state        VARCHAR NOT NULL,
    weight       DOUBLE PRECISION NOT NULL,
    volume       DOUBLE PRECISION NOT NULL,
    create_at    TIMESTAMP,
    modified_at  TIMESTAMP,
    person_fk    UUID,
    FOREIGN KEY (person_fk) REFERENCES person (id)
);

CREATE TABLE IF NOT EXISTS person_detail
(
    id           UUID PRIMARY KEY,
    first_name   VARCHAR,
    last_name    VARCHAR,
    passport_num BIGINT,
    address      VARCHAR,
    person_fk    UUID,
    FOREIGN KEY (person_fk) REFERENCES person (id)
);
