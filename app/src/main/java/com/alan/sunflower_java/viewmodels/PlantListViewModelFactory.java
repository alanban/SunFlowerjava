package com.alan.sunflower_java.viewmodels;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.alan.sunflower_java.data.PlantRepository;

/**
 * @author by bansen
 * date 2018/10/25.
 */
public class PlantListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private PlantRepository repository;

    public PlantListViewModelFactory(PlantRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PlantListViewModel(repository);
    }

}
