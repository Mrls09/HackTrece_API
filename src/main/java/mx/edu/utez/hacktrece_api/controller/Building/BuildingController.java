package mx.edu.utez.hacktrece_api.controller.Building;

import mx.edu.utez.hacktrece_api.controller.Building.DTO.BuildingDto;
import mx.edu.utez.hacktrece_api.model.Building.Building;
import mx.edu.utez.hacktrece_api.services.Building.BuildingServices;
import mx.edu.utez.hacktrece_api.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/building")
@CrossOrigin(value = {"*"})
public class BuildingController {
    @Autowired
    private BuildingServices services;

    @GetMapping("/")
    public ResponseEntity<Response<List<Building>>> getAllBuildings() {
        return new ResponseEntity<>(
                this.services.getAllBuildings(),
                HttpStatus.OK
        );
    }
    @PostMapping("/")
    public ResponseEntity<Response<Building>> addBuilding(@RequestBody BuildingDto dto) {
        return new ResponseEntity<>(
                this.services.insertBuilding(dto.getBuilding()),
                HttpStatus.OK
        );
    }
    @PutMapping("/")
    public ResponseEntity<Response<Building>> updateBuilding(@RequestBody Building building) {
        return new ResponseEntity<>(
                this.services.updateBuilding(building),
                HttpStatus.OK
        );
    }
    @DeleteMapping("/{uid}")
    public ResponseEntity<Response<Boolean>> deleteBuilding(@PathVariable("uid") String uid) {
        return new ResponseEntity<>(
                this.services.deleteBuilding(uid),
                HttpStatus.OK
        );
    }
}
