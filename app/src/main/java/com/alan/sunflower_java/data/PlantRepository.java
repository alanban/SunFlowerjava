package com.alan.sunflower_java.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * @author by bansen
 * date 2018/10/25.
 */
public class PlantRepository {

    private static volatile PlantRepository instance;
    private PlantDao plantDao;

    public static PlantRepository getInstance(PlantDao plantDao) {
        if (instance == null) {
            synchronized (PlantRepository.class) {
                instance = new PlantRepository(plantDao);
            }
        }
        return instance;
    }


    public PlantRepository(PlantDao plantDao) {
        this.plantDao = plantDao;
    }

    public LiveData<Plant> getPlant(String id) {
        return plantDao.getPlant(id);
    }

    public LiveData<List<Plant>> getPlants() {
        return plantDao.getPlants();
    }

    public LiveData<List<Plant>> getPlantsWithGrowZoneNumber(int growZoneNumber) {
        return plantDao.getPlantsWithGrowZoneNumber(growZoneNumber);
    }


}
