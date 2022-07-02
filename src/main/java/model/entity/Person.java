package model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Person {
    private UUID id;

    // TODO: 02.07.2022 add UNIQUE constraint in DB
    private String login;

    // TODO: 01.07.2022 encoding for password later
    private String password;

    private PersonDetails details;

    private final List<Cargo> cargoList = new ArrayList<>();

    public Person() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonDetails getDetails() {
        return details;
    }

    public void setDetails(PersonDetails details) {
        this.details = details;
    }

    public List<Cargo> getCargoList() {
        return new ArrayList<>(cargoList);
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

        return login.equals(person.getLogin());
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", details=" + details +
                '}';
    }
}
