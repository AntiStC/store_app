package model.dto;

import model.entity.Person;
import model.entity.CargoState;
import model.entity.CargoType;

import java.time.LocalDateTime;
import java.util.UUID;

public final class CargoDto {
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

    private CargoDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.type = builder.type;
        this.state = builder.state;
        this.weight = builder.weight;
        this.volume = builder.volume;
        this.createdAt = builder.createdAt;
        this.modifiedAt = builder.modifiedAt;
        this.owner = builder.owner;
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
        return """
                {
                    "id": "%s"
                    "name": "%s",
                    "description": "%s",
                    "cargo_type": %s,
                    "cargo_state": %s,
                    "weight": %s,
                    "volume": %s,
                    "created_at": %s,
                    "modified_at": %s
                }
                """.formatted(id, name, description, type, state, weight, volume, createdAt, modifiedAt);
    }

    public static class Builder {
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

        public Builder setId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setType(CargoType type) {
            this.type = type;
            return this;
        }

        public Builder setState(CargoState state) {
            this.state = state;
            return this;
        }

        public Builder setWeight(Double weight) {
            this.weight = weight;
            return this;
        }

        public Builder setVolume(Double volume) {
            this.volume = volume;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setModifiedAt(LocalDateTime modifiedAt) {
            this.modifiedAt = modifiedAt;
            return this;
        }

        public Builder setOwner(Person owner) {
            this.owner = owner;
            return this;
        }

        public CargoDto build() {
            return new CargoDto(this);
        }
    }
}
