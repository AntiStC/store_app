package entity;

import java.util.Objects;
import java.util.UUID;

public class PersonDetails {
    private UUID id;
    private String firstName;
    private String lastName;
    private Integer passportNum;
    private String address;

    public PersonDetails() {
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

        return getId() == personDetails.getId()
                && Objects.equals(getPassportNum(), personDetails.getPassportNum());
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + passportNum.hashCode();
        return result;
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
}
