package entity.dto;

import java.util.UUID;

public class PersonDetailsDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private Integer passportNum;
    private String address;

    public PersonDetailsDTO() {
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
        if (this == o) return true;
        if (!(o instanceof PersonDetailsDTO that)) return false;

        if (!id.equals(that.id)) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (!passportNum.equals(that.passportNum)) return false;
        return address.equals(that.address);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + passportNum.hashCode();
        result = 31 * result + address.hashCode();
        return result;
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
