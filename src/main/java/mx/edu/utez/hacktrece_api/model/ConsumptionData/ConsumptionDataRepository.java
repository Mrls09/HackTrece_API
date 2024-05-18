package mx.edu.utez.hacktrece_api.model.ConsumptionData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumptionDataRepository extends JpaRepository<ConsumptionData, Long> {

    //day
    @Query(value = "SELECT DATE(c.timestamp) AS day, MAX(c.consumption_value) " +
            "FROM consumption_data  c WHERE building_id = :buildingId  " +
            "GROUP BY DATE(c.timestamp) " +
            "ORDER BY MAX(c.consumption_value) DESC " +
            "LIMIT 1", nativeQuery = true)
    Object findDayWithHighestConsumption(@Param("buildingId") String buildingId);

    //week
    @Query(value = "SELECT YEARWEEK(c.timestamp) AS week, MAX(c.consumption_value) " +
            "FROM consumption_data  c WHERE building_id = :buildingId " +
            "GROUP BY YEARWEEK(c.timestamp) " +
            "ORDER BY MAX(c.consumption_value) DESC " +
            "LIMIT 1", nativeQuery = true)
    String findWeekWithHighestConsumption(@Param("buildingId") String buildingId);

    //Hour
    @Query(value = "SELECT HOUR(c.timestamp) AS hour, MAX(c.consumption_value) " +
            "FROM consumption_data  c WHERE building_id = :buildingId " +
            "GROUP BY HOUR(c.timestamp) " +
            "ORDER BY MAX(c.consumption_value) DESC " +
            "LIMIT 1", nativeQuery = true)
    Object findHourWithHighestConsumption(@Param("buildingId") String buildingId);

    //Month
    @Query(value = "SELECT YEAR(c.timestamp) AS year, MONTH(c.timestamp) AS month, MAX(c.consumption_value) " +
            "FROM consumption_data  c WHERE building_id = :buildingId " +
            "GROUP BY YEAR(c.timestamp), MONTH(c.timestamp) " +
            "ORDER BY MAX(c.consumption_value) DESC " +
            "LIMIT 1", nativeQuery = true)
    Object findMonthWithHighestConsumption(@Param("buildingId") String buildingId);

    @Query(value = "SELECT MONTH(timestamp) AS month, YEAR(timestamp) AS year, SUM(consumption_value) AS totalConsumption " +
            "FROM consumption_data " +
            "WHERE building_id = :buildingId " +
            "GROUP BY YEAR(timestamp), MONTH(timestamp) " +
            "ORDER BY year, month", nativeQuery = true)
    List<Object[]> findMonthlyConsumptionByBuilding(@Param("buildingId") String buildingId);

    @Query(value = "SELECT MONTH(timestamp) AS month, SUM(consumption_value) AS totalConsumption " +
            "FROM consumption_data " +
            "WHERE building_id = :buildingId AND YEAR(timestamp) = :year " +
            "GROUP BY MONTH(timestamp) " +
            "ORDER BY month", nativeQuery = true)
    List<Object[]> findMonthlyConsumptionByBuildingAndYear(@Param("buildingId") String buildingId, @Param("year") int year);

    @Query("SELECT SUM(cd.consumptionValue) FROM ConsumptionData cd WHERE cd.building.id = :buildingId AND MONTH(cd.timestamp) = :month AND YEAR(cd.timestamp) = :year")
    Double findTotalConsumptionByBuildingAndMonth(@Param("buildingId") String buildingId, @Param("month") int month, @Param("year") int year);

    @Query("SELECT MONTH(cd.timestamp) as month, SUM(cd.consumptionValue) as totalConsumption " +
            "FROM ConsumptionData cd " +
            "WHERE cd.building.id = :buildingId AND YEAR(cd.timestamp) = :year " +
            "GROUP BY MONTH(cd.timestamp) " +
            "ORDER BY MONTH(cd.timestamp)")
    List<Object[]> findTotalConsumptionByBuildingAndYear(@Param("buildingId") String buildingId, @Param("year") int year);

    @Query("SELECT c.device.id, SUM(c.consumptionValue) " +
            "FROM ConsumptionData c " +
            "WHERE FUNCTION('MONTH', c.timestamp) = FUNCTION('MONTH', CURRENT_DATE) " +
            "AND FUNCTION('YEAR', c.timestamp) = FUNCTION('YEAR', CURRENT_DATE) " +
            "AND c.device.building.id = :buildingId " +
            "GROUP BY c.device.id")
    List<Object[]> findTotalConsumptionByCurrentMonthAndBuilding(@Param("buildingId") String buildingId);

}
