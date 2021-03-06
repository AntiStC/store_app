package model.entity;

import java.util.Objects;
import java.util.UUID;

public class PersonDetails {
    private UUID id;
    private String firstName;
    private String lastName;
    // TODO: 02.07.2022 add UNIQUE constraint in DB
    private Integer passportNum;
    private String address;

    public PersonDetails() {
    }

    private PersonDetails(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.passportNum = builder.passportNum;
        this.address = builder.address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(Integer passportNum) {
        this.passportNum = passportNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !getClass().equals(o.getClass())) {
            return false;
        }

        PersonDetails personDetails = (PersonDetails) o;

        return Objects.equals(getPassportNum(), personDetails.getPassportNum());
    }

    @Override
    public int hashCode() {
        return passportNum.hashCode();
    }

    @Override
    public String toString() {
        return "PersonDetails{" +
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

        public PersonDetails build() {
            return new PersonDetails(this);
        }
    }
}
