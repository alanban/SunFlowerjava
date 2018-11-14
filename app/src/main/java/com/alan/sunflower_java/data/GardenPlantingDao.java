package com.alan.sunflower_java.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import java.util.List;

/**
 * @author by bansen
 * date 2018/10/23.
 */
@Dao
public interface GardenPlantingDao {

    @Query("SELECT * FROM garden_plantings")
    LiveData<List<GardenPlanting>> getGardenPlantings();

    @Query("SELECT * FROM garden_plantings WHERE id = :gardenPlantingId")
    LiveData<GardenPlanting> getGardenPlanting(Long gardenPlantingId);

    @Query("SELECT * FROM garden_plantings WHERE plant_id = :plantId")
    LiveData<GardenPlanting> getGardenPlantingForPlant(String plantId);

    /**
     * This query will tell Room to query both the [Plant] and [GardenPlanting] tables and handle
     * the object mapping.
     */
    @Transaction
    @Query("SELECT * FROM plants")
    LiveData<List<PlantAndGardenPlantings>> getPlantAndGardenPlantings();

    @Insert
    Long insertGardenPlanting(GardenPlanting gardenPlanting);

    @Delete
    void deleteGardenPlanting(GardenPlanting gardenPlanting);
}
