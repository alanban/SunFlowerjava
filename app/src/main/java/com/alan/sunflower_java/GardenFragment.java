package com.alan.sunflower_java;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alan.sunflower_java.adpaters.GardenPlantDiffCallback;
import com.alan.sunflower_java.adpaters.GardenPlantingAdapter;
import com.alan.sunflower_java.data.GardenPlanting;
import com.alan.sunflower_java.data.PlantAndGardenPlantings;
import com.alan.sunflower_java.databinding.FragmentGardenBinding;
import com.alan.sunflower_java.utilities.InjectorUtils;
import com.alan.sunflower_java.viewmodels.GardenPlantingListViewModel;
import com.alan.sunflower_java.viewmodels.GardenPlantingListViewModelFactory;

import java.util.List;


public class GardenFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentGardenBinding binding = FragmentGardenBinding.inflate(inflater);
        GardenPlantingAdapter gardenPlantingAdapter = new GardenPlantingAdapter(new GardenPlantDiffCallback());
        binding.gardenList.setAdapter(gardenPlantingAdapter);
        subscribeUi(gardenPlantingAdapter, binding);
        return binding.getRoot();
    }

    private void subscribeUi(final GardenPlantingAdapter gardenPlantingAdapter, final FragmentGardenBinding binding) {
        GardenPlantingListViewModelFactory factory = InjectorUtils.provideGardenPlantingListViewModelFactory(requireContext());
        final GardenPlantingListViewModel viewModel = ViewModelProviders.of(this, factory)
                .get(GardenPlantingListViewModel.class);

        viewModel.getGardenPlantings().observe(getViewLifecycleOwner(), new Observer<List<GardenPlanting>>() {
            @Override
            public void onChanged(@Nullable List<GardenPlanting> gardenPlantings) {
                binding.setHasPlantings(gardenPlantings != null && !gardenPlantings.isEmpty());
            }
        });

        viewModel.getPlantAndGardenPlantings().observe(getViewLifecycleOwner(), new Observer<List<PlantAndGardenPlantings>>() {
            @Override
            public void onChanged(@Nullable List<PlantAndGardenPlantings> plantAndGardenPlantings) {
                if (plantAndGardenPlantings!=null&&!plantAndGardenPlantings.isEmpty()) {
                    gardenPlantingAdapter.submitList(plantAndGardenPlantings);
                }
            }
        });
    }

}
