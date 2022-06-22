package mapper;

import entity.Cargo;
import entity.Person;
import entity.PersonDetails;
import entity.dto.CargoDTO;
import entity.dto.PersonDTO;
import entity.dto.PersonDetailsDTO;

public class CustomMapper {

    public static CargoDTO toDTO(Cargo cargo) {
        return new CargoDTO(
                cargo.getId(),
                cargo.getName(),
                cargo.getDescription(),
                cargo.getType(),
                cargo.getState(),
                cargo.getWeight(),
                cargo.getVolume(),
                cargo.getCreatedAt(),
                cargo.getModifiedAt(),
                cargo.getOwner());
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
