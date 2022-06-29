ALTER TABLE cargos ADD FOREIGN KEY (owner_id) REFERENCES persons (id);

ALTER TABLE persons ADD FOREIGN KEY (details_id) REFERENCES person_details (id);

ALTER TABLE persons ADD FOREIGN KEY (cargos_id) REFERENCES cargos(id);
