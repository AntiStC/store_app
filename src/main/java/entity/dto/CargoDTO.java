package entity.dto;

import entity.enums.CargoState;
import entity.enums.CargoType;
import entity.Person;

import java.time.LocalDateTime;
import java.util.UUID;

public class CargoDTO {
    private final UUID id;
    private final String name;
    private final String description;
    private final CargoType type;
    private final CargoState state;
    private final Double weight;
    private final Double volume;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final Person owner;

    public CargoDTO(UUID id, String name, String description, CargoType type, CargoState state, Double weight, Double volume, LocalDateTime createdAt, LocalDateTime modifiedAt, Person owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.state = state;
        this.weight = weight;
        this.volume = volume;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.owner = owner;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CargoType getType() {
        return type;
    }

    public CargoState getState() {
        return state;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getVolume() {
        return volume;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public Person getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "CargoDTO{" +
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
