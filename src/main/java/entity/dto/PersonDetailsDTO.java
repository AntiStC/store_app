package entity.dto;

import java.util.UUID;

public record PersonDetailsDTO(UUID id, String firstName, String lastName, Integer passportNum, String address) {

    @Override
    public String toString() {
        return "PersonDetailsDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passportNum=" + passportNum +
                ", address='" + address + '\'' +
                '}';
    }
}
