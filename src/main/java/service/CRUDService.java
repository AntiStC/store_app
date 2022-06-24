package service;

import java.util.List;
import java.util.UUID;

public interface CRUDService<Entity, DTO> {

    DTO create(Entity entity);

    DTO read(UUID id);

    List<DTO> getAll();

    DTO update(Entity entity);

    void delete(UUID id);

    void deleteAll();
}
