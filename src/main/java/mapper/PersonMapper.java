package mapper;

import model.dto.PersonDto;
import model.entity.Person;

import java.util.stream.Collectors;

public class PersonMapper {
    public static PersonDto toDto(Person person) {
        PersonDto personDto = new PersonDto.Builder()
                .setId(person.getId())
                .setLogin(person.getLogin())
                .setPassword(person.getPassword())
                .setDetails(PersonDetailsMapper.toDto(person.getDetails()))
                .build();

        personDto.addCargos(person.getCargoList().stream()
                .map(CargoMapper::toDto)
                .collect(Collectors.toList()));
        return personDto;
    }

    public static Person toEntity(PersonDto personDto) {
        Person person = new Person();
        person.setId(personDto.getId());
        person.setLogin(personDto.getLogin());
        person.setPassword(personDto.getPassword());
        person.setDetails(PersonDetailsMapper.toEntity(personDto.getDetails()));
        person.addCargos(personDto.getCargoList().stream()
                .map(CargoMapper::toEntity)
                .collect(Collectors.toList()));

        return person;
    }
}
