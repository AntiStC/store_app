package mapper;

import model.dto.PersonDto;
import model.entity.Person;

import java.util.stream.Collectors;

public class PersonMapper {
    public static PersonDto toDto(Person person) {
        PersonDto personDto = new PersonDto(
                person.getId(),
                person.getLogin(),
                person.getPassword(),
                PersonDetailsMapper.toDto(person.getDetails()));

        personDto.addCargos(person.getCargoList().stream()
                .map(CargoMapper::toDto)
                .collect(Collectors.toList()));
        return personDto;
    }

    public static Person toEntity(PersonDto personDto) {
        Person person = new Person();
        person.setId(personDto.getId());
        person.setDetails(PersonDetailsMapper.toEntity(personDto.getDetails()));
        person.addCargos(personDto.getCargoList().stream()
                .map(CargoMapper::toEntity)
                .collect(Collectors.toList()));

        return person;
    }
}
