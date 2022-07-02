package model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Cargo {
    private UUID id;
    // TODO: 02.07.2022 add UNIQUE constraint in DB
    private String name;
    private String description;
    // TODO: 02.07.2022 add UNIQUE constraint in DB
    private CargoType type;
    private CargoState state;
    // TODO: 02.07.2022 add UNIQUE constraint in DB
    private Double weight;
    private Double volume;
    // TODO: 02.07.2022 add UNIQUE constraint in DB
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Person owner;

    public Cargo() {
    }

    private Cargo(Builder builder) {
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

        return name.equals(cargo.getName())
                && weight.equals(cargo.getWeight())
                && type.equals(cargo.getType())
                && (createdAt != null && createdAt.equals(cargo.getCreatedAt()));
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + weight.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
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

        public Cargo build() {
            return new Cargo(this);
        }
    }
}
