package com.rubenpla.develop.techtestapp.util.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rubenpla.develop.techtestapp.mvp.model.Family;

public class SharedPreferencesAgent {

    private final String preferencesFile = "preferencesFile";
    private final String NO_PREFERENCE_SAVED = "NO_PREFERENCE_SAVED";

    private Context context;
    private SharedPreferencesAgent preferenceAgentInstance;
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

    public String getCurrentUserId(String prefKey) {
        String resultPreference = preferences.getString(prefKey, NO_PREFERENCE_SAVED);

        return resultPreference;
    }

    public boolean isProfileSaved() {
        return ((!getCurrentUserId(PreferencesKeys.PREFERENCES_USER_REGISTERED_INFO)
                .equalsIgnoreCase(NO_PREFERENCE_SAVED)));
    }

    /**
     *     Save and get HashMap in SharedPreference
     */

    public void saveProfile(String key , Family profileinfo) {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(profileinfo);
        preferenceEditor.putString(key,json);
        preferenceEditor.apply();     // This line is IMPORTANT !!!
    }

    public Family getProfileSaved(String key) {
        Gson gson = new GsonBuilder().create();
        String json = preferences.getString(key, NO_PREFERENCE_SAVED);

        Family profileInfo = gson.fromJson(json, Family.class);

        return profileInfo;
    }
}
