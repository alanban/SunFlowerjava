package com.alan.sunflower_java.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.view.View;

import com.alan.sunflower_java.R;
import com.alan.sunflower_java.data.GardenPlanting;
import com.alan.sunflower_java.data.Plant;
import com.alan.sunflower_java.data.PlantAndGardenPlantings;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author by bansen
 * date 2018/10/19.
 */

public class PlantAndGardenPlantingsViewModel extends ViewModel {

    private Context context;
    private PlantAndGardenPlantings plantings;

    private Plant plant;
    private GardenPlanting gardenPlanting;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.US);

    private String plantDateString, waterDateString, wateringPrefix, wateringSuffix;

    private ObservableField<String> imageUrl, plantDate, waterDate;

    public PlantAndGardenPlantingsViewModel(Context context, PlantAndGardenPlantings plantings) {
        this.context = context;
        this.plantings = plantings;
        this.plant = plantings.getPlant();
        if (plantings.getGardenPlantings() != null && !plantings.getGardenPlantings().isEmpty()) {
            this.gardenPlanting = plantings.getGardenPlantings().get(0);
            plantDateString = dateFormat.format(gardenPlanting.getPlantDate().getTime());
            waterDateString = dateFormat.format(gardenPlanting.getLastWateringDate().getTime());
            wateringPrefix = context.getResources().getString(R.string.watering_next_prefix, waterDateString);
            wateringSuffix = context.getResources().getQuantityString(R.plurals.watering_next_suffix, plant.getWateringInterval(), plant.getWateringInterval());
        }

    }

    public ObservableField<String> getImageUrl() {
        if (imageUrl == null) {
            imageUrl = new ObservableField<>(plant.getImageUrl());
        }
        return imageUrl;
    }

    public ObservableField<String> getPlantDate() {
        if (plantDate == null) {
            plantDate = new ObservableField<>(context.getResources().getString(R.string.planted_date, plant.getName(),
                    plantDateString));
        }
        return plantDate;
    }

    public ObservableField<String> getWaterDate() {
        if (waterDate == null) {
            waterDate = new ObservableField<>(wateringPrefix + "-" + wateringSuffix);
        }
        return waterDate;
    }
}
