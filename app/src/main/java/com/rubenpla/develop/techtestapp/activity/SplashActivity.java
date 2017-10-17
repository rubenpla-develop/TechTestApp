package com.rubenpla.develop.techtestapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.rubenpla.develop.techtestapp.application.TechTestApplication;
import com.rubenpla.develop.techtestapp.db.DataStoreTables;
import com.rubenpla.develop.techtestapp.db.query.WriteFamilyTableFromJsonAssetsQuery;
import com.rubenpla.develop.techtestapp.mvp.model.Family;
import com.rubenpla.develop.techtestapp.util.AssetFilesManager;

import java.io.IOException;

public class SplashActivity extends AppCompatActivity {

    private final String TAG = SplashActivity.class.getSimpleName();
    private final String JSON_ASSETS_FILE = "family.json";

    private AssetFilesManager assetsManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assetsManager = new AssetFilesManager(this, JSON_ASSETS_FILE);
        if (!TechTestApplication.getSharedPreferencesAgent().isPreferencesAlreadyInit()) {
            try {
                getFamilyListAssets();
            } catch (IOException e) {
                Log.e(TAG, "Exception e :" + e.getLocalizedMessage());
            }
        } else {
            launchHomeActivity();
        }
    }

    @Nullable
    private void getFamilyListAssets() throws IOException {
        String jsonFromAssets = null;
        Family family;

        try {
            jsonFromAssets = assetsManager.readAssetFile();

            if (jsonFromAssets != null && jsonFromAssets.length() > 0) {
                family = parseFamilyAssetsToPojo(jsonFromAssets);
                saveAssetDataToDB(family);
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception e :" + e.getLocalizedMessage());
        }finally {
            TechTestApplication.getSharedPreferencesAgent().saveFirstBootInit(TechTestApplication
                    .getSharedPreferencesAgent().FIRST_BOOT_ALREADY_DONE, true);

            launchHomeActivity();
        }
    }

    @Nullable
    private Family parseFamilyAssetsToPojo(final String jsonString) {
        Gson gson = new Gson();
        Family family = gson.fromJson(jsonString, Family.class);

        return family;
    }

    private void saveAssetDataToDB(Object object) throws Exception {
        WriteFamilyTableFromJsonAssetsQuery query = new WriteFamilyTableFromJsonAssetsQuery();
        DataStoreTables dataStoreTables = new DataStoreTables();

        TechTestApplication.getDataStoreDB().executeWithWritableDB(query,
                dataStoreTables.TABLE_FAMILY_MAIN, object);
    }

    private void launchHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

        finish();
    }
}
