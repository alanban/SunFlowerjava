package com.alan.sunflower_java.workers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.alan.sunflower_java.data.AppDataBase;
import com.alan.sunflower_java.data.Plant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import androidx.work.Worker;

import static com.alan.sunflower_java.utilities.Constants.PLANT_DATA_FILENAME;

/**
 * @author by bansen
 * date 2018/10/23.
 */
public class SeedDatabaseWorker extends Worker {
    private String TAG = SeedDatabaseWorker.class.getSimpleName();

    @NonNull
    @Override
    public Result doWork() {
        Type plantType = new TypeToken<List<Plant>>() {
        }.getType();
        JsonReader jsonReader = null;

        try {
            InputStream inputStream = getApplicationContext().getAssets().open(PLANT_DATA_FILENAME);
            jsonReader = new JsonReader(new InputStreamReader(inputStream));
            List<Plant> plantList = new Gson().fromJson(jsonReader, plantType);
            AppDataBase database = AppDataBase.getInstance(getApplicationContext());
            database.plantDao().insertAll(plantList);
            return Result.SUCCESS;
        } catch (Exception ex) {
            Log.e(TAG, "Error seeding database", ex);
            return Result.FAILURE;
        } finally {
            try {
                jsonReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
