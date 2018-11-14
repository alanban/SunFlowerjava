package com.alan.sunflower_java;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.alan.sunflower_java.adpaters.PlantAdapter;
import com.alan.sunflower_java.adpaters.PlantDiffCallback;
import com.alan.sunflower_java.data.Plant;
import com.alan.sunflower_java.databinding.FragmentPlantListBinding;
import com.alan.sunflower_java.utilities.InjectorUtils;
import com.alan.sunflower_java.viewmodels.PlantListViewModel;
import com.alan.sunflower_java.viewmodels.PlantListViewModelFactory;

import java.util.List;


public class PlantListFragment extends android.support.v4.app.Fragment {

    private PlantListViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentPlantListBinding binding = FragmentPlantListBinding.inflate(inflater, container, false);
        Context context = getContext() == null ? binding.getRoot().getContext() : getContext();

        PlantListViewModelFactory factory = InjectorUtils.providePlantListViewModelFactory(context);
        viewModel = ViewModelProviders.of(this, factory).get(PlantListViewModel.class);

        PlantAdapter adapter = new PlantAdapter(new PlantDiffCallback());
        binding.plantList.setAdapter(adapter);

        subscribeUi(adapter);
        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_plant_list,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.filter_zone) {
            updateData();
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void subscribeUi(final PlantAdapter adapter) {
        viewModel.getPlants().observe(getViewLifecycleOwner(), new Observer<List<Plant>>() {
            @Override
            public void onChanged(@Nullable List<Plant> plants) {
                if (plants!=null) {
                    adapter.submitList(plants);
                }
            }
        });
    }

    private void updateData() {
        if (viewModel.isFiltered()) {
            viewModel.clearGrowZoneNumber();
        }else {
            viewModel.setGrowZoneNumber(9);
        }
    }
}
