package mx.edu.utez.hacktrece_api.model.ConsumptionData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeekConsumption {
    private String startDate;
    private String endDate;
    private double consumptionValue;
}
