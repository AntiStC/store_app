ALTER TABLE cargo ADD FOREIGN KEY (person_fk) REFERENCES person (id);

ALTER TABLE person_detail ADD FOREIGN KEY (person_fk) REFERENCES person (id);

