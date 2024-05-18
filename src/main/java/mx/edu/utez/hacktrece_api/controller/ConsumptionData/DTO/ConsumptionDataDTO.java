package mx.edu.utez.hacktrece_api.controller.ConsumptionData.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.hacktrece_api.model.Building.Building;
import mx.edu.utez.hacktrece_api.model.ConsumptionData.ConsumptionData;
import mx.edu.utez.hacktrece_api.model.ElectronicDevice.ElectronicDevice;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConsumptionDataDTO {
    private Long id;
    private LocalDateTime timestamp;
    private double consumptionValue;
    private ElectronicDevice device;
    private Building building;

    public ConsumptionData getEntity(){
        return new ConsumptionData(
                getId(),
                getTimestamp(),
                getConsumptionValue(),
                getDevice(),
                getBuilding()
        );
    }
}
