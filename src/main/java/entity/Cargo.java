package entity;

import entity.enums.CargoState;
import entity.enums.CargoType;

import java.time.LocalDateTime;
import java.util.UUID;

public class Cargo {
    private UUID id;
    private String name;
    private String description;
    private CargoType type;
    private CargoState state;
    private Double weight;
    private Double volume;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private Person owner;

    public Cargo() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CargoType getType() {
        return type;
    }

    public void setType(CargoType type) {
        this.type = type;
    }

    public CargoState getState() {
        return state;
    }

    public void setState(CargoState state) {
        this.state = state;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !getClass().equals(o.getClass())) {
            return false;
        }

        Cargo cargo = (Cargo) o;

        return getId() == cargo.getId()
                && getOwner().getId() == cargo.getOwner().getId();
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + owner.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", state=" + state +
                ", weight=" + weight +
                ", volume=" + volume +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", owner=" + owner +
                '}';
    }
}
