package com.rubenpla.develop.techtestapp.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesAgent {

    private final String TAG = SharedPreferencesAgent.class.getSimpleName();

    private final String preferencesFile = "preferencesFile";
    public final String FIRST_BOOT_ALREADY_DONE = "FIRST_BOOT_ALREADY_DONE";

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor preferenceEditor;

    public SharedPreferencesAgent(Context context) {
        this.context = context;
        setSharedPreferencesAgent();
    }

    private void setSharedPreferencesAgent() {
        preferences = context.getSharedPreferences(preferencesFile, Context.MODE_PRIVATE);
        preferenceEditor = preferences.edit();
    }

    public boolean isPreferencesAlreadyInit() {
        return preferences.getBoolean(FIRST_BOOT_ALREADY_DONE, false);
    }

    public void saveFirstBootInit(String key , boolean isFirstBoot) {
        preferenceEditor.putBoolean(key,isFirstBoot);
        preferenceEditor.apply();
    }
}
