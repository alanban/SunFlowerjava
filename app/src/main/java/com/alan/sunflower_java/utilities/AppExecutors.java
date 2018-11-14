package com.alan.sunflower_java.utilities;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author by bansen
 * date 2018/11/1.
 */
public class AppExecutors {
    private static ExecutorService IO_EXRCUTOR = Executors.newSingleThreadExecutor();

    public static void runOnIoThread(Runnable runnable){
        IO_EXRCUTOR.submit(runnable);
    }
}
