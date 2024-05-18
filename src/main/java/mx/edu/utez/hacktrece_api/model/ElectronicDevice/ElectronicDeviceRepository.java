package mx.edu.utez.hacktrece_api.model.ElectronicDevice;

import mx.edu.utez.hacktrece_api.model.Building.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ElectronicDeviceRepository extends JpaRepository<ElectronicDevice, String> {

    List<ElectronicDevice> findByBuilding(Building building);

}
