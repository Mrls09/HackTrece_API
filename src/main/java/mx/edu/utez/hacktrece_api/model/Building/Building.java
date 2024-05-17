package mx.edu.utez.hacktrece_api.model.Building;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.hacktrece_api.model.ConsumptionData.ConsumptionData;
import mx.edu.utez.hacktrece_api.model.ElectronicDevice.ElectronicDevice;
import mx.edu.utez.hacktrece_api.model.Reader.ReaderTotal;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Building {
    @Id
    @GeneratedValue(generator = "uuid")
    @UuidGenerator
    @Column(name = "id")
    private String id;
    private String name;
    private String location;
    private double totalConsumption;
    @CreationTimestamp
    private Timestamp created_at;
    @OneToOne(mappedBy = "building", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ReaderTotal readerTotal;
}
