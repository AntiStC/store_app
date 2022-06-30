package mapper;

import model.dto.PersonDetailsDto;
import model.entity.PersonDetails;

public class PersonDetailsMapper {
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
