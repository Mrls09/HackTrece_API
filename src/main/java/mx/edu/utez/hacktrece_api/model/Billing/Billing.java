package mx.edu.utez.hacktrece_api.model.Billing;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.hacktrece_api.model.Building.Building;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Billing {
    @Id
    @GeneratedValue(generator = "uuid")
    @UuidGenerator
    @Column(name = "id")
    private String id;
    @Column()
    private Double cost;
    @Column()
    private String currency;
    @Column()
    private Double priceRate;
    @Column()
    private Double kw_h;
    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp created_at;
    @Column()
    private int year;
    @Column()
    private int month;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;
}
