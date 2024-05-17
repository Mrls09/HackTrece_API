package mx.edu.utez.hacktrece_api.controller.Building.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.hacktrece_api.model.Building.Building;
import mx.edu.utez.hacktrece_api.model.Reader.ReaderTotal;

import java.sql.Timestamp;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BuildingDto {
    private String id;
    private String name;
    private String location;
    private double totalConsumption;
    private Timestamp created_at;
    private ReaderTotal readerTotal;

    public Building getBuilding(){
        return new Building(
                getId(),
                getName(),
                getLocation(),
                getTotalConsumption(),
                getCreated_at(),
                getReaderTotal()
        );
    }
}
