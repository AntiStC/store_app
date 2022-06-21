package entity.dto;

import entity.Cargo;
import entity.PersonDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersonDTO {
    private UUID id;
    private PersonDetails details;
    private List<Cargo> cargos;

    public PersonDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PersonDetails getDetails() {
        return details;
    }

    public void setDetails(PersonDetails details) {
        this.details = details;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }


}
