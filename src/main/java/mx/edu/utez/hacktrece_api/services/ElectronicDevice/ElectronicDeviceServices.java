package mx.edu.utez.hacktrece_api.services.ElectronicDevice;

import mx.edu.utez.hacktrece_api.model.Building.Building;
import mx.edu.utez.hacktrece_api.model.Building.BuildingRepository;
import mx.edu.utez.hacktrece_api.model.ElectronicDevice.ElectronicDevice;
import mx.edu.utez.hacktrece_api.model.ElectronicDevice.ElectronicDeviceRepository;
import mx.edu.utez.hacktrece_api.model.Reader.ReaderElectronic;
import mx.edu.utez.hacktrece_api.services.Reader.ReaderServices;
import mx.edu.utez.hacktrece_api.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ElectronicDeviceServices {
    @Autowired
    private ElectronicDeviceRepository repository;
    @Autowired
    private ReaderServices readerServices;
    @Autowired
    private BuildingRepository buildingRepository;

    @Transactional(readOnly = true)
    public Response<List<ElectronicDevice>> findAllByBuilding(String uid){
        Building building = this.buildingRepository.getReferenceById(uid);
        return new Response<>(
                this.repository.findByBuilding(building),
                false,
                200,
                "Done!"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<String> changeStatus(String uid){
        Optional<ElectronicDevice> exist = this.repository.findById(uid);
        if(exist.isPresent()){
            exist.get().setStatus(!exist.get().getStatus());
            this.repository.saveAndFlush(exist.get());
            return new Response<>(
                    "Done!",
                    false,
                    200,
                    "Done!"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "No found!"
        );
    }
    @Transactional(readOnly = true)
    public Response<List<ElectronicDevice>> findAll() {
        return new Response<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<ElectronicDevice> save(ElectronicDevice electronicDevice) {
        ElectronicDevice electronicDeviceSaved = this.repository.save(electronicDevice);
        ReaderElectronic readerElectronic = new ReaderElectronic();
        readerElectronic.setElectronicDevice(electronicDeviceSaved);
        readerElectronic = this.readerServices.saveReaderElectronic(readerElectronic);
        electronicDeviceSaved.setReaderElectronic(readerElectronic);
        return new Response<>(
                this.repository.saveAndFlush(electronicDeviceSaved),
                false,
                200,
                "DONE!"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<ElectronicDevice> update(ElectronicDevice electronicDevice) {
        if (this.repository.existsById(electronicDevice.getId())) {
            return new Response<>(
                    this.repository.saveAndFlush(electronicDevice),
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
    @Transactional(rollbackFor = {SQLException.class})
    public Response<ElectronicDevice> delete(String id) {
        Optional<ElectronicDevice> electronicDevice = this.repository.findById(id);
        if (electronicDevice.isPresent()) {
            this.repository.deleteById(id);
            return new Response<>(
                    electronicDevice.get(),
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
