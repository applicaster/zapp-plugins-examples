package com.example;


import android.content.Context;

import com.applicaster.plugin_manager.GenericPluginI;
import com.applicaster.plugin_manager.Plugin;
import com.applicaster.plugin_manager.PluginSchemeI;
import com.applicaster.plugin_manager.hook.ApplicationLoaderHookUpI;
import com.applicaster.plugin_manager.hook.HookListener;

import java.util.Map;

/**
 * Created by Avi Levin .
 */
public class exampleAdapter implements GenericPluginI, PluginSchemeI, ApplicationLoaderHookUpI {

    //*********** GenericPluginI Interface implementation ***********//
    /***
     * This function called on plugin invocation
     * @param plugin information about the plugin
     */
    @Override
    public void setPluginModel(Plugin plugin) {
        //TODO: use this to get the metadata for this plugin

    }

    //*********** PluginSchemeI interface implementation ***********//
    /***
     * This method is invoked when a plugin url scheme was called.
     * The format is:
     * <app-url-scheme>://plugin?type=<plugin_type>&plugin_identifier=<plugin_identifier>&morekeyValueParams..
     * @param context generic intro activity
     * @param data url scheme query params
     */
    @Override
    public boolean handlePluginScheme(Context context, Map<String, String> data) {
        //TODO: write your url scheme implementation here, change the bool handled variable accordingly

        return false;
    }

    //*********** ApplicationLoaderHookUpI interface implementation ***********//
    /***
     * This function called after all the data loaded and before main activity opened.
     * @param context generic intro activity
     * @param listener listener to continue the application flow after execution finished.
     */
    @Override
    public void executeOnApplicationReady(Context context, HookListener listener) {
        listener.onHookFinished();
    }

    /***
     * This function called after Plugins loaded, you can add logic that not related to the application data
     * as Zapp strings or applicaster models.
     * @param context APIntroActivity
     * @param listener listener to continue the application flow after execution finished.
     */
    @Override
    public void executeOnStartup(Context context, HookListener listener) {
        //TODO: add your code init phase over here

        listener.onHookFinished();
    }

    /**
     * This interface is being deprecated due to not passing all information about plugin
     * PLEASE USE GenericPluginI instead
     * initialization of the player plugin configuration with a Map params
     *
     * @param params
     */
    @Override
    public void setPluginConfigurationParams(Map params) {
        //TODO: use this if you need to get the plugin configuration params
    }
}
