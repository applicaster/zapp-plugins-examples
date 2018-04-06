package com.applicaster.plugin.example.fullscreen;

import android.support.multidex.MultiDexApplication;

import com.applicaster.plugin_manager.PluginManager;

public class PluginExampleApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        PluginManager.getInstance().configure(this);
    }
}
