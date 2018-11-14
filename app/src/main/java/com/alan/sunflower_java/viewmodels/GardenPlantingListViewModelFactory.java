package com.alan.sunflower_java.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;

import com.alan.sunflower_java.data.GardenPlantingRepository;

/**
 * @author by bansen
 * date 2018/11/7.
 */
public class GardenPlantingListViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private GardenPlantingRepository repository;

    public GardenPlantingListViewModelFactory(GardenPlantingRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GardenPlantingListViewModel(repository);
    }
}
