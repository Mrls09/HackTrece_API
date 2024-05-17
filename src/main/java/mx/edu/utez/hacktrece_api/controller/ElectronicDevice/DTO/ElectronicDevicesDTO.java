package mx.edu.utez.hacktrece_api.controller.ElectronicDevice.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.hacktrece_api.model.Building.Building;
import mx.edu.utez.hacktrece_api.model.ElectronicDevice.ElectronicDevice;
import mx.edu.utez.hacktrece_api.model.Reader.ReaderElectronic;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ElectronicDevicesDTO {
    private String id;
    private String name;
    private String type;
    private double consumption;
    private Timestamp created_at;
    private Building building;
    private ReaderElectronic readerElectronic;

    public ElectronicDevice getElectronicDevice(){
        return new ElectronicDevice(
                getId(),
                getName(),
                getType(),
                getConsumption(),
                getCreated_at(),
                getBuilding(),
                getReaderElectronic()
        );
    }
}
