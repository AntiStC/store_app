package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Person {
    private UUID id;
    //1..1
    private PersonDetails details;
    //1..*
    private List<Cargo> cargos;

    public Person() {
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

    public void addCargo(Cargo cargo) {
        if (cargos == null) {
            cargos = new ArrayList<>();
        }
        cargos.add(cargo);
    }

    public void removeCargo(Cargo cargo) {
        cargos.remove(cargo);
    }
}
