package com.alan.sunflower_java;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.alan.sunflower_java.data.Plant;
import com.alan.sunflower_java.databinding.FragmentPlantDetailBinding;
import com.alan.sunflower_java.utilities.InjectorUtils;
import com.alan.sunflower_java.viewmodels.PlantDetailViewModel;
import com.alan.sunflower_java.viewmodels.PlantDetailViewModelFactory;


public class PlantDetailFragment extends Fragment {

    private String shareText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final String plantId = PlantDetailFragmentArgs.fromBundle(getArguments()).getPlantId();
        PlantDetailViewModelFactory factory = InjectorUtils.providePlantDetailViewModelFactory(requireActivity(), plantId);
        final PlantDetailViewModel plantDetailViewModel = ViewModelProviders.of(this, factory).get(PlantDetailViewModel.class);

        final FragmentPlantDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_plant_detail, container, false);
        binding.setViewModel(plantDetailViewModel);
        binding.setLifecycleOwner(this);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plantDetailViewModel.addPlantToGarden();
                Snackbar.make(view, R.string.added_plant_to_garden, Snackbar.LENGTH_LONG).show();
            }
        });

        plantDetailViewModel.plant.observe(this, new Observer<Plant>() {
            @Override
            public void onChanged(@Nullable Plant plant) {
                shareText = plant != null ? getString(R.string.share_text_plant, plant.getName()) : "";
            }
        });

        setHasOptionsMenu(true);

        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_plant_detail, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item != null && item.getItemId() == R.id.action_share) {
            Intent shareIntent = ShareCompat.IntentBuilder.from(getActivity())
                    .setText(shareText)
                    .setType("text/plain")
                    .createChooserIntent();

            // https://android-developers.googleblog.com/2012/02/share-with-intents.html
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // If we're on Lollipop, we can open the intent as a document
                shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            } else {
                // Else, we will use the old CLEAR_WHEN_TASK_RESET flag
                shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            }
            startActivity(shareIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
