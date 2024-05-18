package mx.edu.utez.hacktrece_api.services.Billing;

import mx.edu.utez.hacktrece_api.model.Billing.Billing;
import mx.edu.utez.hacktrece_api.model.Billing.BillingRepository;
import mx.edu.utez.hacktrece_api.model.Building.Building;
import mx.edu.utez.hacktrece_api.model.Building.BuildingRepository;
import mx.edu.utez.hacktrece_api.model.ConsumptionData.ConsumptionDataRepository;
import mx.edu.utez.hacktrece_api.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class BillingServices {
    @Autowired
    private BillingRepository repository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired private ConsumptionDataRepository consumptionDataRepository;

    Double pricesRate[] = {1.019,  1.243 , 3.633 };


    public Response<Billing> generateCurrency(String uid_building){
        Billing billing = new Billing();
        Building building = this.buildingRepository.getReferenceById(uid_building);
        billing.setBuilding(building);
        billing.setKw_h(building.getTotalConsumption());
        double[] cost = this.calculatorCost(building.getTotalConsumption());
        billing.setCost(Double.valueOf(String.format("%.2f", cost[0])));
        billing.setPriceRate(cost[1]);
        billing.setCurrency("MXN");
        return new Response<>(
                this.repository.save(billing),
                false,
                200,
                "Done!"
        );
    }
    public Response<List<Billing>> generateAboutYear(String uid_building) {
        Building building = this.buildingRepository.getReferenceById(uid_building);
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        List<Object[]> consumptionList = this.consumptionDataRepository.findTotalConsumptionByBuildingAndYear(uid_building, currentYear);

        Map<Integer, Double> consumptionByMonth = new HashMap<>();
        for (Object[] result : consumptionList) {
            Integer month = (Integer) result[0];
            Double totalConsumption = (Double) result[1];
            consumptionByMonth.put(month, totalConsumption);
        }

        // Lista para almacenar las facturas
        List<Billing> billings = new ArrayList<>();

        // Generar facturas mensuales
        for (Map.Entry<Integer, Double> entry : consumptionByMonth.entrySet()) {
            Integer month = entry.getKey();
            Double totalKwConsumption = entry.getValue();

            double[] cost = this.calculatorCost(totalKwConsumption);

            Billing billing = new Billing();
            billing.setBuilding(building);
            billing.setKw_h(totalKwConsumption);
            billing.setCost(Double.valueOf(String.format("%.2f", cost[0])));
            billing.setPriceRate(cost[1]);
            billing.setCurrency("MXN");
            billing.setYear(currentYear);
            billing.setMonth(month);

            billings.add(billing);
        }

        return new Response<>(
                billings,
                false,
                200,
                "OK"
        );
    }


    public double[] calculatorCost(Double totalKwConsumption){
        if(totalKwConsumption <= 1500){
            return new double[]{totalKwConsumption * pricesRate[0], pricesRate[0]};
        }else if(totalKwConsumption > 1500  && totalKwConsumption <= 3760){
            return new double[]{totalKwConsumption * pricesRate[1], pricesRate[1]};
        }else if(totalKwConsumption > 3760 ){
            return new double[]{totalKwConsumption * pricesRate[2], pricesRate[2]};
        }else{
            return null;
        }
    }
}
