package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Person {
    private UUID id;

    private PersonDetails details;

    //todo make final
    private List<Cargo> cargoList = new ArrayList<>();

    public Person(long id) {
    }

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

    public List<Cargo> getCargoList() {
        return cargoList;
    }

    //todo will be removed
    public void setCargoList(List<Cargo> cargoList) {
        this.cargoList = cargoList;
    }

    public void addCargo(Cargo cargo) {
        if (cargo.getOwner() == null) {
            cargo.setOwner(this);
        }
        cargoList.add(cargo);
    }

    public void addCargos(List<Cargo> cargos) {
        cargos.forEach(cargo -> cargo.setOwner(this));
        cargoList.addAll(cargos);
    }

    public void removeCargo(Cargo cargo) {
        cargoList.remove(cargo);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !getClass().equals(o.getClass())) {
            return false;
        }

        Person person = (Person) o;

        return getId() == person.getId();
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", details=" + details +
                '}';
    }
}
