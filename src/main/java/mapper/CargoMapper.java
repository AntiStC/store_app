package mapper;

import model.dto.CargoDto;
import model.entity.Cargo;

public class CargoMapper {
    public static CargoDto toDTO(Cargo cargo) {
        return new CargoDto.Builder()
                .setId(cargo.getId())
                .setName(cargo.getName())
                .setDescription(cargo.getDescription())
                .setType(cargo.getType())
                .setState(cargo.getState())
                .setWeight(cargo.getWeight())
                .setVolume(cargo.getVolume())
                .setCreatedAt(cargo.getCreatedAt())
                .setModifiedAt(cargo.getModifiedAt())
                .setOwner(cargo.getOwner())
                .build();
    }

    public static Cargo toEntity(CargoDto cargoDto) {
        return new Cargo.Builder()
                .setId(cargoDto.getId())
                .setName(cargoDto.getName())
                .setDescription(cargoDto.getDescription())
                .setType(cargoDto.getType())
                .setState(cargoDto.getState())
                .setWeight(cargoDto.getWeight())
                .setVolume(cargoDto.getVolume())
                .setCreatedAt(cargoDto.getCreatedAt())
                .setModifiedAt(cargoDto.getModifiedAt())
                .setOwner(cargoDto.getOwner())
                .build();
    }
}
