package mapper;

import model.dto.PersonDetailsDto;
import model.entity.Cargo;
import model.entity.Person;
import model.entity.PersonDetails;
import model.dto.CargoDto;
import model.dto.PersonDto;

import java.util.stream.Collectors;

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

    public static Cargo toEntity(CargoDto cargoDto) {
        return new Cargo.Builder()
                .setId(cargoDto.getId())
                .setName(cargoDto.getName())
                .setDescription(cargoDto.getDescription())
                .setType(cargoDto.getType())
                .setState(cargoDto.getState())
                .setWeight(cargoDto.getWeight())
                .setVolume(cargoDto.getVolume())
                .setCreatedAt(cargoDto.getCreatedAt())
                .setModifiedAt(cargoDto.getModifiedAt())
                .setOwner(cargoDto.getOwner())
                .build();
    }

    public static PersonDto toDTO(Person person) {
        PersonDto personDto = new PersonDto(
                person.getId(),
                CustomMapper.toDTO(person.getDetails()));

        personDto.addCargos(person.getCargoList().stream()
                .map(CustomMapper::toDTO)
                .collect(Collectors.toList()));
        return personDto;
    }

    public static Person toEntity(PersonDto personDto) {
        Person person = new Person();
        person.setId(personDto.getId());
        person.setDetails(toEntity(personDto.getDetails()));
        person.addCargos(personDto.getCargoList().stream()
                .map(CustomMapper::toEntity)
                .collect(Collectors.toList()));

        return person;
    }

    public static PersonDetailsDto toDTO(PersonDetails personDetails) {
        return new PersonDetailsDto.Builder()
                .setId(personDetails.getId())
                .setFirstName(personDetails.getFirstName())
                .setLastName(personDetails.getLastName())
                .setPassportNum(personDetails.getPassportNum())
                .setAddress(personDetails.getAddress())
                .build();
    }

    public static PersonDetails toEntity(PersonDetailsDto personDetailsDto) {
        return new PersonDetails.Builder()
                .setId(personDetailsDto.getId())
                .setFirstName(personDetailsDto.getFirstName())
                .setLastName(personDetailsDto.getLastName())
                .setPassportNum(personDetailsDto.getPassportNum())
                .setAddress(personDetailsDto.getAddress())
                .build();
    }
}
