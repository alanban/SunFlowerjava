package com.alan.sunflower_java.utilities;

import android.content.Context;

import com.alan.sunflower_java.data.AppDataBase;
import com.alan.sunflower_java.data.GardenPlantingRepository;
import com.alan.sunflower_java.data.PlantRepository;
import com.alan.sunflower_java.viewmodels.GardenPlantingListViewModelFactory;
import com.alan.sunflower_java.viewmodels.PlantDetailViewModelFactory;
import com.alan.sunflower_java.viewmodels.PlantListViewModelFactory;

/**
 * @author by bansen
 * date 2018/10/24.
 */
public class InjectorUtils {
    private static PlantRepository getPlantRepository(Context context) {
        return PlantRepository.getInstance(AppDataBase.getInstance(context).plantDao());
    }

    private static GardenPlantingRepository getGardenPlantingRepository(Context context) {
        return GardenPlantingRepository.getInstance(
                AppDataBase.getInstance(context).gardenPlantingDao());
    }

    public static GardenPlantingListViewModelFactory provideGardenPlantingListViewModelFactory(Context context) {
        GardenPlantingRepository repository = getGardenPlantingRepository(context);
        return new GardenPlantingListViewModelFactory(repository);
    }

    public static PlantListViewModelFactory providePlantListViewModelFactory(Context context) {
        PlantRepository repository = getPlantRepository(context);
        return new PlantListViewModelFactory(repository);
    }

    public static PlantDetailViewModelFactory providePlantDetailViewModelFactory(Context context, String plantId) {
        return new PlantDetailViewModelFactory(getPlantRepository(context), getGardenPlantingRepository(context), plantId);
    }
}
