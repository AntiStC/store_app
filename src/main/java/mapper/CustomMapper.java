package mapper;

import entity.Cargo;
import entity.Person;
import entity.PersonDetails;
import entity.dto.CargoDTO;
import entity.dto.PersonDTO;
import entity.dto.PersonDetailsDTO;

public class CustomMapper {

    public static CargoDTO toDTO(Cargo cargo) {
        CargoDTO cargoDTO = new CargoDTO();
        cargoDTO.setId(cargo.getId());
        cargoDTO.setName(cargo.getName());
        cargoDTO.setDescription(cargo.getDescription());
        cargoDTO.setType(cargo.getType());
        cargoDTO.setState(cargo.getState());
        cargoDTO.setWeight(cargo.getWeight());
        cargoDTO.setVolume(cargo.getVolume());
        cargoDTO.setCreatedAt(cargo.getCreatedAt());
        cargoDTO.setModifiedAt(cargo.getModifiedAt());
        cargoDTO.setOwner(cargo.getOwner());
        return cargoDTO;
    }

    public static PersonDTO toDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setDetails(person.getDetails());
        personDTO.setCargos(person.getCargos());
        return personDTO;
    }

    public static PersonDetailsDTO toDTO(PersonDetails personDetails) {
        PersonDetailsDTO personDetailsDTO = new PersonDetailsDTO();
        personDetailsDTO.setId(personDetails.getId());
        personDetailsDTO.setFirstName(personDetails.getFirstName());
        personDetailsDTO.setLastName(personDetails.getLastName());
        personDetailsDTO.setPassportNum(personDetails.getPassportNum());
        personDetailsDTO.setAddress(personDetails.getAddress());
        return personDetailsDTO;
    }
}
