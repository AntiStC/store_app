package service;

public interface CRUDService<Entity, DTO> {

    DTO create(Entity entity);

    DTO read(Entity entity);

    DTO update(Entity entity);

    boolean delete(Entity entity);
}
