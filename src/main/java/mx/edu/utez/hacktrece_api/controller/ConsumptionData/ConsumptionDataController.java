package mx.edu.utez.hacktrece_api.controller.ConsumptionData;

import mx.edu.utez.hacktrece_api.controller.ConsumptionData.DTO.ConsumptionDataDTO;
import mx.edu.utez.hacktrece_api.model.ConsumptionData.ConsumptionData;
import mx.edu.utez.hacktrece_api.model.ConsumptionData.ConsumptionSummary;
import mx.edu.utez.hacktrece_api.services.ConsumptionData.ConsumptionDataService;
import mx.edu.utez.hacktrece_api.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/consumption-data")
@CrossOrigin(value = {"*"})
public class ConsumptionDataController {
    @Autowired
    private ConsumptionDataService service;

    @GetMapping("/most-consumption/{uid}")
    public ResponseEntity<Response<ConsumptionSummary>> getHourDayWeekMonth(@PathVariable("uid") String uid){
        return new ResponseEntity<>(
                this.service.getHourWeekDayMothHighest(uid),
                HttpStatus.OK
        );
    }
    @GetMapping("/monthly/{uid}")
    public ResponseEntity<Response<List<Object[]>>> getMonthly(@PathVariable("uid") String uid){
        return new ResponseEntity<>(
                this.service.getMonthlyConsumptionByBuilding(uid),
                HttpStatus.OK
        );
    }
    @GetMapping("/monthly-year/{uid}")
    public ResponseEntity<Response<List<Object[]>>> getMonthlyYear(@PathVariable("uid") String uid){
        return new ResponseEntity<>(
                this.service.getMonthlyConsumptionByBuildingAndYear(uid, 2024),
                HttpStatus.OK
        );
    }
    @PostMapping("/")
    public ResponseEntity<Response<ConsumptionData>> save(@RequestBody ConsumptionDataDTO dto){
        return new ResponseEntity<>(
                this.service.insert(dto.getEntity()),
                HttpStatus.OK
        );
    }
    @GetMapping("/month-devices/{uidBuilding}")
    public ResponseEntity<Response<List<Object[]>>> getTotalConsumptionByCurrentMonthAndBuilding(@PathVariable("uidBuilding") String uidBuilding){
        return new ResponseEntity<>(
                this.service.getTotalConsumptionByCurrentMonthAndBuilding(uidBuilding),
                HttpStatus.OK
        );
    }
}
