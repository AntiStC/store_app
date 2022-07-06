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

    private PersonDto(Builder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
        this.details = builder.details;
        this.cargoList = builder.cargoList;
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
        return "PersonDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static class Builder {
        private UUID id;
        private String login;
        private String password;
        private PersonDetailsDto details;
        private List<CargoDto> cargoList = new ArrayList<>();

        public Builder setId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setDetails(PersonDetailsDto details) {
            this.details = details;
            return this;
        }

        public Builder setCargoList(List<CargoDto> cargoList) {
            this.cargoList = cargoList;
            return this;
        }

        public PersonDto build() {
            return new PersonDto(this);
        }
    }
}
