package mx.edu.utez.hacktrece_api.model.ConsumptionData;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.hacktrece_api.model.ElectronicDevice.ElectronicDevice;
import mx.edu.utez.hacktrece_api.model.Building.Building;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "consumption_data")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ConsumptionData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime timestamp;

    private double consumptionValue;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = true)
    private ElectronicDevice device;

    @ManyToOne
    @JoinColumn(name = "building_id", nullable = true)
    private Building building;
}
