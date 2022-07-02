package model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersonDto {

    private final UUID id;

    private final String login;
    private final String password;

    private final PersonDetailsDto details;

    private final List<CargoDto> cargoList;

    private PersonDto(UUID id, String login, String password, PersonDetailsDto details, List<CargoDto> cargoList) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.details = details;
        this.cargoList = cargoList;
    }

    public void addCargo(CargoDto cargo) {
        cargoList.add(cargo);
    }

    public void addCargos(List<CargoDto> cargos) {
        cargoList.addAll(cargos);
    }

    public void removeCargo(CargoDto cargo) {
        cargoList.remove(cargo);
    }

    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public PersonDetailsDto getDetails() {
        return details;
    }

    public List<CargoDto> getCargoList() {
        return new ArrayList<>(cargoList);
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", details=" + details +
                '}';
    }

    public static class Builder {
        private UUID id;
        private String login;
        private String password;
        private PersonDetailsDto details;
        private List<CargoDto> cargoList = new ArrayList<>();

        public void setId(UUID id) {
            this.id = id;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setDetails(PersonDetailsDto details) {
            this.details = details;
        }

        public void setCargoList(List<CargoDto> cargoList) {
            this.cargoList = cargoList;
        }

        public PersonDto build() {
            return new PersonDto(id, login, password, details, cargoList);
        }
    }
}
