package com.applicaster.plugin.example.fullscreen;

import android.content.Context;

import com.applicaster.plugin_manager.PluginSchemeI;

import java.util.Map;

public class PluginSchemeHandler implements PluginSchemeI {

    @Override
    public boolean handlePluginScheme(Context context, Map<String, String> data) {
        boolean verified = false;

        // This will handle the following URLSchema sampleApp://plugin?type=ui_component&action=open
        // Remember that you can define any type of custom "action" for your specific case.
        if ("ui_component".equals(data.get("type"))) {
            if ("open".equals(data.get("action"))) {
                verified = true;
                context.startActivity(FullScreenActivity.getCallingIntent(context));
            }
        }

        return verified;
    }
}
