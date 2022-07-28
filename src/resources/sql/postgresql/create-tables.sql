CREATE TABLE IF NOT EXISTS person_detail
(
    person_detail_id UUID PRIMARY KEY,
    first_name       VARCHAR,
    last_name        VARCHAR,
    passport_num     BIGINT,
    address          VARCHAR
);

CREATE TABLE IF NOT EXISTS person
(
    person_id        UUID PRIMARY KEY,
    login            VARCHAR UNIQUE NOT NULL,
    password         VARCHAR UNIQUE NOT NULL,
    person_detail_fk UUID
);

CREATE TABLE IF NOT EXISTS cargo
(
    cargo_id    UUID PRIMARY KEY,
    name        VARCHAR,
    description VARCHAR,
    type        VARCHAR          NOT NULL,
    state       VARCHAR          NOT NULL,
    weight      DOUBLE PRECISION NOT NULL,
    volume      DOUBLE PRECISION NOT NULL,
    create_at   TIMESTAMPTZ      NOT NULL DEFAULT NOW(),
    modified_at TIMESTAMPTZ      NOT NULL DEFAULT NOW(),
    person_fk   UUID
);

CREATE TABLE IF NOT EXISTS cargo_list
(
    cargo_list_fk  UUID UNIQUE NOT NULL,
    person_list_fk UUID UNIQUE NOT NULL
);

ALTER TABLE cargo_list
    ADD FOREIGN KEY (person_list_fk) REFERENCES person (person_id),
    ADD FOREIGN KEY (cargo_list_fk) REFERENCES cargo (cargo_id);

ALTER TABLE person
    ADD FOREIGN KEY (person_detail_fk) REFERENCES person_detail (person_detail_id);

ALTER TABLE cargo
    ADD FOREIGN KEY (person_fk) REFERENCES cargo_list (person_list_fk);

SET TIMEZONE = 'Europe/Moscow';
