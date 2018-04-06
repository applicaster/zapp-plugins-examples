package com.applicaster.plugin.example.fullscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView();
    }

    private void setupView() {
        Button button = findViewById(R.id.btn_call_schema);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // For simplicity reason we decided to call the handler directly from here, but in
                // a real world case scenario you would need to define a url like the following
                // sampleApp://plugin?type=ui_component&action=open to be consumed by the Zapp
                // Application.
                PluginSchemeHandler pluginSchemeHandler = new PluginSchemeHandler();
                HashMap<String, String> schemaParams = new HashMap<>();
                schemaParams.put("type", "ui_component");
                schemaParams.put("action", "open");

                pluginSchemeHandler.handlePluginScheme(MainActivity.this, schemaParams);
            }
        });
    }
}
