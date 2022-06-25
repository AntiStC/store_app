package mapper;

import entity.Cargo;
import entity.Person;
import entity.PersonDetails;
import dto.CargoDto;
import dto.PersonDto;

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

    public static PersonDto toDTO(Person person) {
        PersonDto personDto = new PersonDto(
                person.getId(),
                CustomMapper.toDTO(person.getDetails()));

        personDto.addCargos(person.getCargoList().stream()
                .map(CustomMapper::toDTO)
                .collect(Collectors.toList()));
        return personDto;
    }

    public static dto.PersonDetailsDto toDTO(PersonDetails personDetailsDto) {
        return new dto.PersonDetailsDto.Builder()
                .setId(personDetailsDto.getId())
                .setFirstName(personDetailsDto.getFirstName())
                .setLastName(personDetailsDto.getLastName())
                .setPassportNum(personDetailsDto.getPassportNum())
                .setAddress(personDetailsDto.getAddress())
                .build();
    }
}
