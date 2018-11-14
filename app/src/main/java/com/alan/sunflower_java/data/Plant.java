package com.alan.sunflower_java.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Calendar;

import static java.util.Calendar.DAY_OF_YEAR;

/**
 * @author by bansen
 * date 2018/10/19.
 */
@Entity(tableName = "plants")
public class Plant {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    String plantId;
    String name;
    String imageUrl;
    String description;
    int growZoneNumber;
    int wateringInterval = 7;// how often the plant should be watered, in days

    /**
     * Determines if the plant should be watered.  Returns true if today's date > date of last
     * watering + watering Interval; false otherwise.
     * 确定植物是否应该浇水。如果今天的日期>最后一次浇水的日期+浇水间隔，则返回true;否则false。
     */
    public boolean shouldBeWatered(Calendar lastWateringDate) {
        lastWateringDate.add(DAY_OF_YEAR, wateringInterval);
        return Calendar.getInstance().after(lastWateringDate);
    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGrowZoneNumber() {
        return growZoneNumber;
    }

    public void setGrowZoneNumber(int growZoneNumber) {
        this.growZoneNumber = growZoneNumber;
    }

    public int getWateringInterval() {
        return wateringInterval;
    }

    public void setWateringInterval(int wateringInterval) {
        this.wateringInterval = wateringInterval;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
