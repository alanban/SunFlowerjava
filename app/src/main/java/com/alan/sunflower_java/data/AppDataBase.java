package com.alan.sunflower_java.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import com.alan.sunflower_java.workers.SeedDatabaseWorker;


import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import static com.alan.sunflower_java.utilities.Constants.DATABASE_NAME;


/**
 * @author by bansen
 * date 2018/10/19.
 *
 * The Room database for this app
 */
@Database(entities = {GardenPlanting.class, Plant.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class AppDataBase extends RoomDatabase {
    public abstract GardenPlantingDao gardenPlantingDao();
    public abstract PlantDao plantDao();

    // For Singleton instantiation

    private static volatile AppDataBase instance;

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance==null) {
            instance = buildDatabase(context);
        }
        return instance;
    }

    // Create and pre-populate the database. See this article for more details:
    // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
    private static AppDataBase buildDatabase(Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        WorkRequest request = new OneTimeWorkRequest.Builder(SeedDatabaseWorker.class).build();
                        WorkManager.getInstance().enqueue(request);
                    }
                }).build();
    }
}
