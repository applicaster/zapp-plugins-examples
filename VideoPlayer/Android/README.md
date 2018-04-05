# Zapp Player Plugin Example - Android

This is an example project for creating a player plugin for the Applicaster Zapp Platform. You can use this example project as a reference to build and test your own player plugin. 

# Prepare App for local building 

#### Set Up Bintray Variables
Bintray credentials enable the build to pull Applicaster SDK dependencies from Maven.

1. Add `MAVEN_USERNAME` and `MAVEN_PASSWORD` entries to your environment variables.
2. Contact an Applicaster developer to obtain credentials for the above entries.

# Project Structure 

### App Module

Contains mock implementation of how the Zapp Platform creates a playable item and how it creates a player contract to launch the player plugin. 

*This portion of the code facilitates testing and should only be modified to change the playable item you need to pass to your plugin.*

### TestPlayerPlugin-Android Module

This module contains a sample player plugin in the following classes:

* **TestPlayerAdapter.java**:
    * Extends `BasePlayer` which implements the `PlayerContract`.
    * Contains the initialization of the plugin and the plugin configurations
    * Contains methods to initialize fullscreen or inline playback.

* **TestPlayerActivity.java**:
    * Contains sample player logic utilizing a simple `VideoView` with a `MediaController` (this is where you can put all the player specific functionality).

# Getting Started 

1. Create a new module that will replace the `TestPlayerPlugin` module. It should contain an adapter which extends the `BasePlayer` use the `TestPlayerAdapter.java` as a template.
2. Modify the `plugin_configurations.json` to adapt to your player. Add any necessary configurations to the manifest. More info about the manifest can be found [here](http://zapp-tech-book.herokuapp.com/zappifest/plugins-manifest-format.html) 
3. Modify the app modules `MainActivity` to pass you the required playable.
4. Test that both the fullscreen and inline functionality work as expected.
5. Continue to the deployment documentation (TBD)
