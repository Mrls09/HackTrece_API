package mx.edu.utez.hacktrece_api.model.ElectronicDevice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.hacktrece_api.model.Building.Building;
import mx.edu.utez.hacktrece_api.model.Reader.ReaderElectronic;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Timestamp;


@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ElectronicDevice {
    @Id
    @GeneratedValue(generator = "uuid")
    @UuidGenerator
    @Column(name = "id")
    private String id;
    @Column()
    private String name;
    @Column()
    private String type;
    @Column(nullable = false)
    private Boolean status;
    private double consumption;
    @CreationTimestamp
    private Timestamp created_at;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;
    @OneToOne(mappedBy = "electronicDevice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ReaderElectronic readerElectronic;
}
