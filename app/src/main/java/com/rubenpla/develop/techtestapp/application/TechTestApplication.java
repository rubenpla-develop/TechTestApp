package com.rubenpla.develop.techtestapp.application;

import android.app.Application;
import android.content.Context;

import com.rubenpla.develop.techtestapp.util.preferences.SharedPreferencesAgent;

public class TechTestApplication extends Application {

    private final String TAG = TechTestApplication.class.getSimpleName();

    private SharedPreferencesAgent preferencesAgent;

    @Override
    public void onCreate() {
        super.onCreate();

        initSharedPreferencesController(this);
    }

    private void initSharedPreferencesController(Context context) {
        preferencesAgent = new SharedPreferencesAgent(context);


    }
}

