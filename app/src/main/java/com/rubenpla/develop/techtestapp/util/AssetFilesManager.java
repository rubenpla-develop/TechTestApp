package com.rubenpla.develop.techtestapp.util;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

public class AssetFilesManager {

    private final String TAG = AssetFilesManager.class.getSimpleName();

    private AssetManager assetsManager;
    private Context context;
    private String assetFile;

    public AssetFilesManager(Context context, String assetFile) {
        this.context = context;
        this.assetFile = assetFile;

        assetsManager = context.getAssets();
    }

    public String readAssetFile() throws IOException, JSONException {
        String fileContent;

        InputStream input = assetsManager.open(assetFile);
        int size = input.available();

        byte[] buffer = new byte[size];
        input.read(buffer);
        input.close();

        fileContent = new String(buffer, "UTF-8");

        return fileContent;
    }
}
