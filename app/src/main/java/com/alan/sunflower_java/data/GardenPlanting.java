package com.alan.sunflower_java.data;

/**
 * @author by bansen
 * date 2018/10/19.
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Calendar;

/**
 * [GardenPlanting] represents when a user adds a [Plant] to their garden, with useful metadata.
 Properties such as [lastWateringDate] are used for notifications (such as when to water the
 plant).
 [GardenPlanting]表示用户将[Plant]添加到他们的花园时，使用有用的元数据。诸如[lastWateringDate]之类的属性用于通知（例如何时为工厂浇水）。
 *
 * Declaring the column info allows for the renaming of variables without implementing a
  database migration, as the column name would not change.
 声明列信息允许重命名变量而不实现数据库迁移，因为列名称不会更改
 */

@Entity(
        tableName = "garden_plantings",
        foreignKeys = @ForeignKey(entity = Plant.class, parentColumns = "id", childColumns = "plant_id"),
        indices = @Index("plant_id")
        )
public class GardenPlanting {

    @ColumnInfo(name = "plant_id")
    String plantId;

    /**w
     * Indicates when the [Plant] was planted. Used for showing notification when it's time
     to harvest the plant.
     表示种植[植物]的时间。用于在收获工厂时显示通知。
     */
    @ColumnInfo(name = "plant_date")
    Calendar plantDate = Calendar.getInstance();

    /**
     * Indicates when the [Plant] was last watered. Used for showing notification when it's
     time to water the plant.
     [Plant]最后一次浇水的时间。用于在给工厂浇水时显示通知
     */
    @ColumnInfo(name = "last_watering_date")
    Calendar lastWateringDate;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    Long gardenPlantingId;

    public GardenPlanting(String plantId) {
        this.plantId = plantId;
        this.plantDate = Calendar.getInstance();
        this.lastWateringDate = Calendar.getInstance();
    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public Calendar getPlantDate() {
        return plantDate;
    }

    public void setPlantDate(Calendar plantDate) {
        this.plantDate = plantDate;
    }

    public Calendar getLastWateringDate() {
        return lastWateringDate;
    }

    public void setLastWateringDate(Calendar lastWateringDate) {
        this.lastWateringDate = lastWateringDate;
    }

    public Long getGardenPlantingId() {
        return gardenPlantingId;
    }

    public void setGardenPlantingId(Long gardenPlantingId) {
        this.gardenPlantingId = gardenPlantingId;
    }
}
