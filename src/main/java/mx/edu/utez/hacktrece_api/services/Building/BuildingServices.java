package mx.edu.utez.hacktrece_api.services.Building;

import mx.edu.utez.hacktrece_api.model.Building.Building;
import mx.edu.utez.hacktrece_api.model.Building.BuildingRepository;
import mx.edu.utez.hacktrece_api.model.Reader.ReaderTotal;
import mx.edu.utez.hacktrece_api.services.Reader.ReaderServices;
import mx.edu.utez.hacktrece_api.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class BuildingServices {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private ReaderServices readerServices;

    @Transactional(readOnly = true)
    public Response<List<Building>> getAllBuildings() {
        return new Response<>(
                this.buildingRepository.findAll(),
                false,
                200,
                "OK"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Building> insertBuilding(Building building) {
        Building buildingSave = this.buildingRepository.save(building);
        ReaderTotal readerTotal = new ReaderTotal();
        readerTotal.setBuilding(buildingSave);
        readerTotal = this.readerServices.saveReaderTotal(readerTotal);
        buildingSave.setReaderTotal(readerTotal);
        return new Response<>(
                this.buildingRepository.saveAndFlush(buildingSave),
                false,
                200,
                "OK"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Building> updateBuilding(Building building) {
        if (this.buildingRepository.findById(building.getId()).isPresent()) {
            return new Response<>(
                    this.buildingRepository.saveAndFlush(building),
                    false,
                    200,
                    "UPDATE"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "Error"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<Boolean> deleteBuilding(String uuid) {
        if (this.buildingRepository.findById(uuid).isPresent()) {
            this.buildingRepository.delete(this.buildingRepository.findById(uuid).get());
            return new Response<>(
                    true,
                    false,
                    200,
                    "DONE!"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "Error!"
        );
    }
}
