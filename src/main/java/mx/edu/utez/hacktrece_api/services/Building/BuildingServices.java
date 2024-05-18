package mx.edu.utez.hacktrece_api.services.Building;

import mx.edu.utez.hacktrece_api.model.Building.Building;
import mx.edu.utez.hacktrece_api.model.Building.BuildingRepository;
import mx.edu.utez.hacktrece_api.model.ConsumptionData.ConsumptionDataRepository;
import mx.edu.utez.hacktrece_api.model.Reader.ReaderTotal;
import mx.edu.utez.hacktrece_api.services.Reader.ReaderServices;
import mx.edu.utez.hacktrece_api.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BuildingServices {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private ReaderServices readerServices;
    @Autowired
    private ConsumptionDataRepository consumptionDataRepository;

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
    public Response<Building> getOne(String uid){
        Optional<Building> exist = this.buildingRepository.findById(uid);
        if(exist.isPresent()){
            List<Object[]> objects = this.consumptionDataRepository.findMonthlyConsumptionByBuilding(uid);
            List<Object> secondPositionValues = new ArrayList<>();
            for (Object[] objArray : objects) {
                if (objArray.length > 2) {
                    secondPositionValues.add(objArray[2]);
                } else {
                    secondPositionValues.add(null);
                }
            }
            double consumption = (double) secondPositionValues.get(secondPositionValues.size()-1);
            exist.get().setTotalConsumption(consumption);
            Building update = this.buildingRepository.saveAndFlush(exist.get());
            return new Response<>(
                    update,
                    false,
                    200,
                    "Done!"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "Error!"
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
