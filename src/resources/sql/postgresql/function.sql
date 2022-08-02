SET TIMEZONE = 'Europe/Moscow';

CREATE FUNCTION upd_trig() RETURNS trigger
    LANGUAGE plpgsql AS
$$
BEGIN
    NEW.modified_at := current_timestamp;
    RETURN NEW;
END
$$;

CREATE TRIGGER upd_trig
    BEFORE UPDATE
    ON cargo
    FOR EACH ROW
EXECUTE PROCEDURE upd_trig();

CREATE FUNCTION create_trig() RETURNS trigger
    LANGUAGE plpgsql AS
$$
BEGIN
    NEW.create_at := current_timestamp;
    RETURN NEW;
END
$$;

CREATE TRIGGER create_trig
    BEFORE INSERT
    ON cargo
    FOR EACH ROW
EXECUTE PROCEDURE create_trig();