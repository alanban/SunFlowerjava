package com.alan.sunflower_java.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.alan.sunflower_java.data.GardenPlantingRepository;
import com.alan.sunflower_java.data.PlantRepository;

/**
 * @author by bansen
 * date 2018/11/1.
 */
public class PlantDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private GardenPlantingRepository gardenPlantingRepository;
    private PlantRepository plantRepository;
    private String plantId;

    public PlantDetailViewModelFactory( PlantRepository plantRepository,GardenPlantingRepository gardenPlantingRepository, String plantId) {
        this.gardenPlantingRepository = gardenPlantingRepository;
        this.plantRepository = plantRepository;
        this.plantId = plantId;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new PlantDetailViewModel(plantRepository,gardenPlantingRepository,plantId);
    }
}
