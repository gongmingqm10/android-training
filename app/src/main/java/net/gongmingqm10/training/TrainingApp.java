package net.gongmingqm10.training;

import android.app.Application;

public class TrainingApp extends Application {

    private static TrainingApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static TrainingApp getInstance() {
        return instance;
    }
}
