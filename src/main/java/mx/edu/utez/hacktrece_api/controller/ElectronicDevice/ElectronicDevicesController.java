package mx.edu.utez.hacktrece_api.controller.ElectronicDevice;

import lombok.Getter;
import mx.edu.utez.hacktrece_api.controller.ElectronicDevice.DTO.ElectronicDevicesDTO;
import mx.edu.utez.hacktrece_api.model.ElectronicDevice.ElectronicDevice;
import mx.edu.utez.hacktrece_api.services.ElectronicDevice.ElectronicDeviceServices;
import mx.edu.utez.hacktrece_api.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/electronic-device")
@CrossOrigin(value = {"*"})
public class ElectronicDevicesController {
    @Autowired
    private ElectronicDeviceServices services;

    @GetMapping("/")
    public ResponseEntity<Response<List<ElectronicDevice>>> getAllDevices() {
        return new ResponseEntity<>(
                this.services.findAll(),
                HttpStatus.OK
        );
    }
    @GetMapping("/building/{uid}")
    public ResponseEntity<Response<List<ElectronicDevice>>> getAllDevicesByBuilding(@PathVariable("uid") String uid){
        return new ResponseEntity<>(
                this.services.findAllByBuilding(uid),
                HttpStatus.OK
        );
    }
    @PostMapping("/")
    public ResponseEntity<Response<ElectronicDevice>> addDevice(@RequestBody ElectronicDevicesDTO device) {
        return new ResponseEntity<>(
                this.services.save(device.getElectronicDevice()),
                HttpStatus.CREATED
        );
    }
    @DeleteMapping("/{uid}")
    public ResponseEntity<Response<ElectronicDevice>> deleteDevice(@PathVariable("uid") String uid) {
        return new ResponseEntity<>(
                this.services.delete(uid),
                HttpStatus.OK
        );
    }
}
