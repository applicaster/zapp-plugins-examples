package com.applicaster.plugin.example.fullscreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.applicaster.plugin_manager.PluginSchemeI;

import java.util.Map;

public class FullScreenActivity extends AppCompatActivity implements PluginSchemeI {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, FullScreenActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
    }

    @Override
    public boolean handlePluginScheme(Context context, Map<String, String> data) {
        return false;
    }
}
