package mx.edu.utez.hacktrece_api.model.Reader;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.hacktrece_api.model.Building.Building;

@Entity
@Table(name = "reader_total")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReaderTotal extends Reader {
    @OneToOne
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    private Building building;
}
