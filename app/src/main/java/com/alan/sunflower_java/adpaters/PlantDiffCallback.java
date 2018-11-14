package com.alan.sunflower_java.adpaters;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.alan.sunflower_java.data.Plant;

/**
 * @author by bansen
 * date 2018/10/26.
 */
public class PlantDiffCallback extends DiffUtil.ItemCallback<Plant> {
    @Override
    public boolean areItemsTheSame(@NonNull Plant plant, @NonNull Plant t1) {
        return plant.getPlantId().equals(t1.getPlantId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Plant plant, @NonNull Plant t1) {
        return plant == t1;
    }
}
