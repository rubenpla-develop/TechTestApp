package com.rubenpla.develop.techtestapp.application;

import android.app.Application;
import android.content.Context;

import com.rubenpla.develop.techtestapp.db.DataStore;
import com.rubenpla.develop.techtestapp.preferences.SharedPreferencesAgent;

public class TechTestApplication extends Application {

    private final String TAG = TechTestApplication.class.getSimpleName();

    private static SharedPreferencesAgent preferencesAgent;
    private static DataStore dataStoreDB;

    @Override
    public void onCreate() {
        super.onCreate();

        initSharedPreferencesController(this.getApplicationContext());
        initDataStoreDB(this.getApplicationContext());
    }

    private void initSharedPreferencesController(Context context) {
        preferencesAgent = new SharedPreferencesAgent(context);
    }

    private void initDataStoreDB(Context context) {
        dataStoreDB = new DataStore(context, DataStore.APP_DATABASE_NAME);
    }

    public static SharedPreferencesAgent getSharedPreferencesAgent() {
        return preferencesAgent;
    }

    public static DataStore getDataStoreDB() {
        return dataStoreDB;
    }
}

