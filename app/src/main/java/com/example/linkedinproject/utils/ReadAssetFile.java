package com.example.linkedinproject.utils;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public final class ReadAssetFile {

    public  static InputStream getFileData(Context context) {
        try {
            return context.getAssets().open("sample.json");
        } catch (IOException e) {
            Log.e("SampleApp", "Exception", e);
        }
        return null;
    }
}
