package com.applicaster.plugin.example.fullscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.applicaster.loader.json.APPluginsLoader;
import com.applicaster.plugin_manager.PluginManager;
import com.applicaster.plugin_manager.playersmanager.internal.PlayersManager;
import com.applicaster.util.asynctask.AsyncTaskListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView();

        result = urlSchemePolicy.handleSpecialScheme(context, origUrl);
    }

    private void setupView() {
        Button button = findViewById(R.id.btn_call_schema);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PluginSchemeHandler pluginSchemeHandler = new PluginSchemeHandler();
                HashMap<String, String> schemaParams = new HashMap<>();
                schemaParams.put("type", "ui_component");
                schemaParams.put("action", "open");

                pluginSchemeHandler.handlePluginScheme(MainActivity.this, schemaParams);
            }
        });
    }
}
