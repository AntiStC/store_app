package mapper;

import entity.Cargo;
import entity.Person;
import entity.PersonDetails;
import dto.CargoDto;
import dto.PersonDTO;
import dto.PersonDetailsDTO;

public class CustomMapper {

    public static CargoDto toDTO(Cargo cargo) {
        return new CargoDto.Builder()
                .setId(cargo.getId())
                .setName(cargo.getName())
                .setDescription(cargo.getDescription())
                .setType(cargo.getType())
                .setState(cargo.getState())
                .setWeight(cargo.getWeight())
                .setVolume(cargo.getVolume())
                .setCreatedAt(cargo.getCreatedAt())
                .setModifiedAt(cargo.getModifiedAt())
                .setOwner(cargo.getOwner())
                .build();
    }

    public static PersonDTO toDTO(Person person) {
        return new PersonDTO(
                person.getId(),
                person.getDetails(),
                person.getCargoList());
    }

    public static PersonDetailsDTO toDTO(PersonDetails personDetails) {
        return new PersonDetailsDTO(
                personDetails.getId(),
                personDetails.getFirstName(),
                personDetails.getLastName(),
                personDetails.getPassportNum(),
                personDetails.getAddress());
    }
}
