package com.alan.sunflower_java.viewmodels;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.alan.sunflower_java.data.GardenPlanting;
import com.alan.sunflower_java.data.GardenPlantingRepository;
import com.alan.sunflower_java.data.PlantAndGardenPlantings;

import java.util.Iterator;
import java.util.List;

/**
 * @author by bansen
 * date 2018/11/7.
 */
public class GardenPlantingListViewModel extends ViewModel {

    private LiveData<List<GardenPlanting>> gardenPlantings ;
    private LiveData<List<PlantAndGardenPlantings>> plantAndGardenPlantings;

    public GardenPlantingListViewModel(GardenPlantingRepository gardenPlantingRepository) {
        this.gardenPlantings = gardenPlantingRepository.getGardenPlantings();
        this.plantAndGardenPlantings = Transformations.map(gardenPlantingRepository.getPlantAndGardenPlantings(), new Function<List<PlantAndGardenPlantings>, List<PlantAndGardenPlantings>>() {
            @Override
            public List<PlantAndGardenPlantings> apply(List<PlantAndGardenPlantings> input) {
                Iterator<PlantAndGardenPlantings> it = input.iterator();
                while( it.hasNext() ) {
                    PlantAndGardenPlantings plantAndGardenPlantings = it.next();
                    if( plantAndGardenPlantings.getGardenPlantings().isEmpty() ) it.remove();
                }

                return input;
            }
        });
    }

    public LiveData<List<GardenPlanting>> getGardenPlantings() {
        return gardenPlantings;
    }

    public LiveData<List<PlantAndGardenPlantings>> getPlantAndGardenPlantings() {
        return plantAndGardenPlantings;
    }
}
