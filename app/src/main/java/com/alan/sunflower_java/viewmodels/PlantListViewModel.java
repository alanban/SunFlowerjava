package com.alan.sunflower_java.viewmodels;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.alan.sunflower_java.data.Plant;
import com.alan.sunflower_java.data.PlantRepository;

import java.util.List;

/**
 * @author by bansen
 * date 2018/10/24.
 */
public class PlantListViewModel extends ViewModel {

    private static int NO_GROW_ZONE = -1;
    private PlantRepository plantRepository;
    private MutableLiveData<Integer> growZoneNumber = new MutableLiveData<>();
    /**
     * MediatorLiveData 可以用来正确的处理其他多个LiveData的事件变化，并处理这些事件
     * 同时 MediatorLiveData 会将自身的 "active/inactive" 状态变化正确的传递给它所处理的LiveData
     */
    private MediatorLiveData<List<Plant>> plantList = new MediatorLiveData<List<Plant>>();

    public PlantListViewModel(final PlantRepository plantRepo) {
        this.plantRepository = plantRepo;

        growZoneNumber.setValue(NO_GROW_ZONE);

        /**
         * 变换liveData
         * 实例化一个新的liveData对象 livePlantList，livePlantList由growZoneNumber变换生成
         * livePlantList将响应growZoneNumber的变化
         */
        LiveData<List<Plant>> livePlantList = Transformations.switchMap(growZoneNumber, new Function<Integer, LiveData<List<Plant>>>() {
            @Override
            public LiveData<List<Plant>> apply(Integer input) {
                if (input == NO_GROW_ZONE) {
                    return plantRepository.getPlants();
                } else {
                    return plantRepository.getPlantsWithGrowZoneNumber(input);
                }
            }
        });

        /**
         * 为MediatorLiveData对象 plantList 添加观察目标
         * 此时 plantList将响应 livePlantList 的onChange事件
         */
        plantList.addSource(livePlantList, new Observer<List<Plant>>() {
            @Override
            public void onChanged(@Nullable List<Plant> plants) {
                plantList.setValue(plants);
            }
        });

        /**
         * 此时当growZoneNumber变化时，livePlantList将会更新;plantList由于观察了livePlantList，也会更新。
         */
    }

    public MediatorLiveData<List<Plant>> getPlants() {
        return plantList;
    }

    public void setGrowZoneNumber(int num) {
        growZoneNumber.setValue(num);
    }

    public void clearGrowZoneNumber() {
        growZoneNumber.setValue(NO_GROW_ZONE);
    }

    public boolean isFiltered() {
        return growZoneNumber.getValue() != null && growZoneNumber.getValue() != NO_GROW_ZONE;
    }

}
