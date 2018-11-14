package com.alan.sunflower_java.adpaters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alan.sunflower_java.R;
import com.alan.sunflower_java.data.PlantAndGardenPlantings;
import com.alan.sunflower_java.databinding.ListItemGardenPlantingBinding;
import com.alan.sunflower_java.viewmodels.PlantAndGardenPlantingsViewModel;

/**
 * @author by bansen
 * date 2018/10/23.
 */
public class GardenPlantingAdapter extends ListAdapter<PlantAndGardenPlantings, GardenPlantingAdapter.ViewHolder> {


    public GardenPlantingAdapter(@NonNull DiffUtil.ItemCallback<PlantAndGardenPlantings> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ViewHolder(ListItemGardenPlantingBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(getItem(i));
        viewHolder.binding.getRoot().setTag(getItem(i));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ListItemGardenPlantingBinding binding;

        public ViewHolder(ListItemGardenPlantingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(PlantAndGardenPlantings plantings) {
            binding.setViewModel(new PlantAndGardenPlantingsViewModel(itemView.getContext(), plantings));
            binding.executePendingBindings();
        }
    }
}
