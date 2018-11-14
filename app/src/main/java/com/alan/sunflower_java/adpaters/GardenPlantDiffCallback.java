package com.alan.sunflower_java.adpaters;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.alan.sunflower_java.data.PlantAndGardenPlantings;

/**
 * @author by bansen
 * date 2018/11/7.
 */
public class GardenPlantDiffCallback extends DiffUtil.ItemCallback<PlantAndGardenPlantings> {
    @Override
    public boolean areItemsTheSame(@NonNull PlantAndGardenPlantings plantAndGardenPlantings, @NonNull PlantAndGardenPlantings t1) {
        return plantAndGardenPlantings.getPlant().getPlantId().equals(t1.getPlant().getPlantId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull PlantAndGardenPlantings plantAndGardenPlantings, @NonNull PlantAndGardenPlantings t1) {
        return plantAndGardenPlantings.getPlant() == (t1.getPlant());
    }
}
