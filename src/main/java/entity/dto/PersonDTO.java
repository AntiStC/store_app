package entity.dto;

import entity.Cargo;
import entity.PersonDetails;

import java.util.List;
import java.util.UUID;

public class PersonDTO {
    private final UUID id;
    private final PersonDetails details;
    private final List<Cargo> cargos;

    public PersonDTO(UUID id, PersonDetails details, List<Cargo> cargos) {
        this.id = id;
        this.details = details;
        this.cargos = cargos;
    }

    public UUID getId() {
        return id;
    }

    public PersonDetails getDetails() {
        return details;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", details=" + details +
                '}';
    }
}
