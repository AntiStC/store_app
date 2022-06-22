package entity.dto;

import entity.enums.CargoState;
import entity.enums.CargoType;
import entity.Person;

import java.time.LocalDateTime;
import java.util.UUID;

public record CargoDTO(UUID id, String name, String description, CargoType type, CargoState state, Double weight,
                       Double volume, LocalDateTime createdAt, LocalDateTime modifiedAt, Person owner) {

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
