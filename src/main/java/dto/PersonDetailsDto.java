package dto;


import entity.PersonDetails;

import java.util.UUID;

public class PersonDetailsDto {

    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final Integer passportNum;
    private final String address;

    private PersonDetailsDto(
            UUID id,
            String firstName,
            String lastName,
            Integer passportNum,
            String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNum = passportNum;
        this.address = address;
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


    public static class Builder {
        private UUID id;
        private String firstName;
        private String lastName;
        private Integer passportNum;
        private String address;

        public Builder setId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setPassportNum(Integer passportNum) {
            this.passportNum = passportNum;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public PersonDetailsDto build() {
            return new PersonDetailsDto(
                    id,
                    firstName,
                    lastName,
                    passportNum,
                    address);
        }
    }
}
