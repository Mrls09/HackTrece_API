package mx.edu.utez.hacktrece_api.model.ElectronicDevice;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.hacktrece_api.model.Building.Building;
import mx.edu.utez.hacktrece_api.model.ConsumptionData.ConsumptionData;
import mx.edu.utez.hacktrece_api.model.Reader.Reader;
import mx.edu.utez.hacktrece_api.model.Reader.ReaderElectronic;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ElectronicDevice {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private UUID id;
    private Long uid;
    @Column()
    private String name;
    @Column()
    private String type;
    @Column(columnDefinition = "VARCHAR(255)")
    private String uid_reader;
    private double consumption;
    @CreationTimestamp
    private Timestamp created_at;
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;
    @OneToOne(mappedBy = "electronicDevice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ReaderElectronic readerElectronic;
}
