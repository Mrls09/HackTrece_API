package mx.edu.utez.hacktrece_api.model.Reader;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.hacktrece_api.model.ElectronicDevice.ElectronicDevice;

@Entity
@Table(name = "reader_electronic")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReaderElectronic extends Reader{
    @OneToOne
    @JoinColumn(name = "electronic_device_id", referencedColumnName = "id")
    private ElectronicDevice electronicDevice;
}
