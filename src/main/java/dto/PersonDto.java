package dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersonDto {

    private final UUID id;

    private final PersonDetailsDto details;

    private final List<CargoDto> cargoList = new ArrayList<>();

    public PersonDto(UUID id, PersonDetailsDto details) {
        this.id = id;
        this.details = details;
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

    public PersonDetailsDto getDetails() {
        return details;
    }

    public List<CargoDto> getCargoList() {
        return cargoList;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", details=" + details +
                '}';
    }
}
