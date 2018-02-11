Zapp Plugin Player Example Android 
==================

The ZappPluginPlayerExample-Android is an example project for creating a player plugin for the Applicaster Zapp Platform. You can use this example project as a reference for how to build your own video player plugin.

If you are not familiar with Zapp, please visit [Applicaster](http://www.applicaster.com) website for more details. The full [Zapp](http://zapp.applicaster.com) plugins documentation is available from within the Zapp system.

### ZappPlayerAdapter
* Implement PlayerContract with Android native VideoView and MediaController.
* Could work as an inline player. Inline players show video content directly from within the screen the user was navigating, in the cell where the content was triggered from, without having to open a new screen specifically for the video player.

### Configuration
* This plugin doesn't need any param configuration.

## Getting Started
Clone the strater-kit project `git clone https://github.com/applicaster/zapp-android-plugins-starter-kit` and folow the instructions in README.

### How to create Plugin Player
1. Replace .git folder
2. Rename 'ZappPluginPlayerExample-Android'
3. Rename 'com.applicaster.pluginplayerexample' package
4. Update ZappPlayerAapter path in proguard-rules.pro
5. Update gradle.properties
6. Update Readme.md
7. Change ZappPlayerAapter implementation

### Add plugin params:
In plugin_configurations.json, add your prameters in a similar way as the example params. You receive the params in setPluginConfigurationParams function: 
```java
    /**
     * initialization of the player plugin configuration with a Map params.
     * the params taken from res/raw/plugin_configurations.json
     *
     * @param params
     */
    @Override
    public void setPluginConfigurationParams(Map params) {
        super.setPluginConfigurationParams(params);
    }
```

### Enjoy coding!
