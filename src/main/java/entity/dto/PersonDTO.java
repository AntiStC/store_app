package entity.dto;

import entity.Cargo;
import entity.PersonDetails;

import java.util.List;
import java.util.UUID;

public record PersonDTO(UUID id, PersonDetails details, List<Cargo> cargos) {

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", details=" + details +
                '}';
    }
}
