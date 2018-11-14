package com.alan.sunflower_java.data;

import android.arch.lifecycle.LiveData;

import com.alan.sunflower_java.utilities.AppExecutors;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * @author by bansen
 * date 2018/11/1.
 */
public class GardenPlantingRepository {
    private GardenPlantingDao gardenPlantingDao;

    public GardenPlantingRepository(GardenPlantingDao gardenPlantingDao) {
        this.gardenPlantingDao = gardenPlantingDao;
    }

    public void createGardenPlanting(final String plantId) {
        AppExecutors.runOnIoThread(new Runnable() {
            @Override
            public void run() {
                GardenPlanting gardenPlanting = new GardenPlanting(plantId);
                gardenPlantingDao.insertGardenPlanting(gardenPlanting);
            }
        });
    }

    public void removeGardenPlanting(final GardenPlanting gardenPlanting) {
        AppExecutors.runOnIoThread(new Runnable() {
            @Override
            public void run() {
                gardenPlantingDao.deleteGardenPlanting(gardenPlanting);
            }
        });
    }

    public LiveData<GardenPlanting> getGardenPlantingForPlant(String plantId) {
        return gardenPlantingDao.getGardenPlantingForPlant(plantId);
    }

    public LiveData<List<GardenPlanting>> getGardenPlantings() {
        return gardenPlantingDao.getGardenPlantings();
    }

    public LiveData<List<PlantAndGardenPlantings>> getPlantAndGardenPlantings() {
        return gardenPlantingDao.getPlantAndGardenPlantings();
    }


    private static volatile GardenPlantingRepository instance;

    public static GardenPlantingRepository getInstance(GardenPlantingDao gardenPlantingDao) {
        if (instance == null) {
            synchronized (GardenPlantingRepository.class) {
                instance = new GardenPlantingRepository(gardenPlantingDao);
            }
        }

        return instance;
    }
}
