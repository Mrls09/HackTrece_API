package mx.edu.utez.hacktrece_api.services.ConsumptionData;
import mx.edu.utez.hacktrece_api.model.ConsumptionData.ConsumptionData;
import mx.edu.utez.hacktrece_api.model.ConsumptionData.ConsumptionDataRepository;
import mx.edu.utez.hacktrece_api.model.ConsumptionData.ConsumptionSummary;
import mx.edu.utez.hacktrece_api.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
public class ConsumptionDataService {
    @Autowired
    private ConsumptionDataRepository repository;

    @Transactional(readOnly = true)
    public Response<ConsumptionSummary> getHourWeekDayMothHighest(String uid){
        Object hour = repository.findHourWithHighestConsumption(uid);
        Object day = repository.findDayWithHighestConsumption(uid);
        String week = repository.findWeekWithHighestConsumption(uid);
        Object month = repository.findMonthWithHighestConsumption(uid);

        ConsumptionSummary summary = new ConsumptionSummary(hour, day, week, month);
        return new Response<>(
                summary,
                false,
                200,
                "OK"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public Response<ConsumptionData> insert(ConsumptionData data){
        return new Response<>(
                this.repository.save(data),
                false,
                200,
                "Done!"
        );
    }
    @Transactional(readOnly = true)
    public Response<List<Object[]>> getMonthlyConsumptionByBuilding(String buildingId) {
        List<Object[]> consumptionData = repository.findMonthlyConsumptionByBuilding(buildingId);
        return new Response<>(
                consumptionData,
                false,
                200,
                "OK"
        );
    }
    @Transactional(readOnly = true)
    public Response<List<Object[]>> getMonthlyConsumptionByBuildingAndYear(String buildingId, int year) {
        List<Object[]> consumptionData = repository.findMonthlyConsumptionByBuildingAndYear(buildingId, year);
        return new Response<>(
                consumptionData,
                false,
                200,
                "OK"
        );
    }
    public Response<List<Object[]>> getTotalConsumptionByCurrentMonthAndBuilding(String buildingId) {
        List<Object[]> totalConsumptionList = repository.findTotalConsumptionByCurrentMonthAndBuilding(buildingId);
        return new Response<>(
                totalConsumptionList,
                false,
                200,
                "OK"
        );
    }

}
