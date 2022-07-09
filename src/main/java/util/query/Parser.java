package util.query;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.dto.CargoDto;
import model.dto.PersonDetailsDto;
import model.dto.PersonDto;
import model.entity.CargoState;
import model.entity.CargoType;

import java.io.BufferedReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Parser {

    public static PersonDto parseEntry(BufferedReader reader) {
        JsonObject person = JsonParser.parseReader(reader).getAsJsonObject();
        JsonObject personDetails = person.getAsJsonObject("user_details");
        JsonArray cargoList = person.getAsJsonArray("cargo_list");
        List<CargoDto> cargoDtoList = new ArrayList<>();

        cargoList.forEach(cargo -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            cargoDtoList.add(new CargoDto.Builder()
                    .setName(cargo.getAsJsonObject().get("name").getAsString())
                    .setDescription(cargo.getAsJsonObject().get("description").getAsString())
                    .setType(CargoType.valueOf(cargo.getAsJsonObject().get("cargo_type").getAsString()))
                    .setState(CargoState.valueOf(cargo.getAsJsonObject().get("cargo_state").getAsString()))
                    .setWeight(cargo.getAsJsonObject().get("weight").getAsDouble())
                    .setVolume(cargo.getAsJsonObject().get("volume").getAsDouble())
                    .setCreatedAt(LocalDateTime.parse(cargo.getAsJsonObject().get("created_at").getAsString(), formatter))
                    .setModifiedAt(LocalDateTime.parse(cargo.getAsJsonObject().get("modified_at").getAsString(), formatter))
                    .build());
        });

        PersonDetailsDto personDetailsDto = new PersonDetailsDto.Builder()
                .setFirstName(personDetails.get("first_name").getAsString())
                .setLastName(personDetails.get("last_name").getAsString())
                .setPassportNum(personDetails.get("passport_no").getAsInt())
                .setAddress(personDetails.get("address").getAsString())
                .build();

        return new PersonDto.Builder()
                .setLogin(person.get("login").getAsString())
                .setPassword(person.get("password").getAsString())
                .setDetails(personDetailsDto)
                .setCargoList(cargoDtoList)
                .build();
    }

    public static UUID getIdFromPath(String path) {
        String[] split = path.split("/");

        if (split[split.length - 1].length() > 20) {
            return UUID.fromString(split[split.length - 1]);
        } else {
            return null;
        }
    }
}
