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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonDTO personDTO)) return false;

        if (!id.equals(personDTO.id)) return false;
        if (!details.equals(personDTO.details)) return false;
        return cargos.equals(personDTO.cargos);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + details.hashCode();
        result = 31 * result + cargos.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", details=" + details +
                '}';
    }
}
