package mx.edu.utez.hacktrece_api.model.ConsumptionData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumptionSummary {
    private Object hour;
    private Object day;
    private String week;
    private Object month;
}
