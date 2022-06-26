package service;

import java.util.List;
import java.util.UUID;

public interface CRUDService<DTO> {

    DTO create(DTO dto);

    DTO read(UUID id);

    List<DTO> getAll();

    DTO update(DTO dto);

    void delete(UUID id);

    void deleteAll();
}
