package entity.dto;

import java.util.UUID;

public class PersonDetailsDTO {
    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final Integer passportNum;
    private final String address;

    public PersonDetailsDTO(UUID id, String firstName, String lastName, Integer passportNum, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNum = passportNum;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getPassportNum() {
        return passportNum;
    }

    public String getAddress() {
        return address;
    }

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
