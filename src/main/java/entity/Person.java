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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;

        if (!id.equals(person.id)) return false;
        if (!details.equals(person.details)) return false;
        return cargos.equals(person.cargos);
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
        return "Person{" +
                "id=" + id +
                ", details=" + details +
                '}';
    }
}
