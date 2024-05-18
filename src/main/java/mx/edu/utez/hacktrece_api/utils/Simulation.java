package mx.edu.utez.hacktrece_api.utils;

import mx.edu.utez.hacktrece_api.services.ConsumptionData.ConsumptionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Simulation {
    @Autowired
    private ConsumptionDataService consumptionDataService;


}
