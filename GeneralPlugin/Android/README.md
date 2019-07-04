# General Plugin example - Android

This project serves as a base example on implementing a general plugin for Android - with focus on using URL schemes and application lifecycle in order to launch activities.

This example plugin implements the following 3 protocols that can be found in Zapp plugins repository:
* `GenericPluginI` - Implement this interface to get the plugin parameters after the initiation phase.
* `PluginSchemeI` - Implement this interface to handle calls for the Zapp URL scheme.
* `ApplicationLoaderHookUpI` - Implement this interface in case you need to add soemting in the applicastion lifecycle.

## Test your implementation with a development project

* Download a development project from Zapp
* Go to app/assets/applicaster.properties and set/add: `avoidRemotePluginConfigurationsFetching=true`.
* Go to app/res/raw/plugin_configurations.json and add this JSON object to the bottom on the JSON array: 

    ```
    { 
        "plugin": {
            "api": {
                "require_startup_execution": true,
                "class_name": "com.example.exampleAdapter"
            },
            "author_name": "developer E-mail",
            "manifest_version": "0.1.0",
            "name": "name of your plugin",
            "description": "short plugin description",
            "type": "general",
            "identifier": "string based identifier",
            "ui_builder_support": false,
            "whitelisted_account_ids": [],
            "min_zapp_sdk": "6.5.0",
            "react_native": true/false,
            "screen": true/false,
            "react_bundle_url": {}
        }
    }
    ```
* Connect your plugin to the development app by adding the module in the project and app level.
    * Go to the app `setting.gradle` file and append the plugin as new project to the app, for example:
        ```
        include <other_values>, ':myPlugin'
        project(':myPlugin').projectDir = new File('mainPluginFolder')
        ```
    * Connect the plugin the the applevel gradle file by setting the following following:
        ```
        implementation (project(':onspota')) {
            exclude group: 'com.applicaster',
            module: 'applicaster-android-sdk'
        }
        ```