package com.alan.sunflower_java.adpaters;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alan.sunflower_java.PlantListFragmentDirections;
import com.alan.sunflower_java.data.Plant;
import com.alan.sunflower_java.databinding.ListItemPlantBinding;

import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

/**
 * @author by bansen
 * date 2018/10/25.
 */
public class PlantAdapter extends ListAdapter<Plant, PlantAdapter.ViewHolder> {

    public PlantAdapter(@NonNull DiffUtil.ItemCallback<Plant> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public PlantAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(ListItemPlantBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlantAdapter.ViewHolder viewHolder, int i) {
        Plant plant = getItem(i);
        viewHolder.bind(createOnClickListener(plant.getPlantId()), plant);
        viewHolder.binding.getRoot().setTag(plant);
    }

    private View.OnClickListener createOnClickListener(final String plantId) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections navDirections = PlantListFragmentDirections.actionPlantListFragmentToPlantDetailFragment(plantId);
                Navigation.findNavController(view).navigate(navDirections);
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ListItemPlantBinding binding;

        public ViewHolder(ListItemPlantBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(View.OnClickListener listener, Plant item) {
            binding.setClickListener(listener);
            binding.setPlant(item);
            binding.executePendingBindings();
        }
    }


}
