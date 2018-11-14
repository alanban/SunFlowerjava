package com.alan.sunflower_java.viewmodels;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.alan.sunflower_java.data.GardenPlanting;
import com.alan.sunflower_java.data.GardenPlantingRepository;
import com.alan.sunflower_java.data.Plant;
import com.alan.sunflower_java.data.PlantRepository;

/**
 * @author by bansen
 * date 2018/11/1.
 */
public class PlantDetailViewModel extends ViewModel {
    private GardenPlantingRepository gardenPlantingRepository;
    private PlantRepository plantRepository;
    private String plantId;

    public LiveData<Boolean> isPlanted;
    public LiveData<Plant> plant;

    public PlantDetailViewModel( PlantRepository plantRepository,GardenPlantingRepository gardenPlantingRepository, String plantId) {
        this.gardenPlantingRepository = gardenPlantingRepository;
        this.plantRepository = plantRepository;
        this.plantId = plantId;

        /* The getGardenPlantingForPlant method returns a LiveData from querying the database. The
         * method can return null in two cases: when the database query is running and if no records
         * are found. In these cases isPlanted is false. If a record is found then isPlanted is
         * true. */
        final LiveData<GardenPlanting> gardenPlantingForPlant = gardenPlantingRepository.getGardenPlantingForPlant(plantId);
        isPlanted = Transformations.map(gardenPlantingForPlant, new Function<GardenPlanting, Boolean>() {
            @Override
            public Boolean apply(GardenPlanting input) {
                return input!=null;
            }
        });
        plant = plantRepository.getPlant(plantId);

    }

    public void addPlantToGarden(){
        gardenPlantingRepository.createGardenPlanting(plantId);
    }
}
