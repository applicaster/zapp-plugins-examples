# Zapp Full Screen Plugin Example - Android

This is an example project for creating a full screen plugin for the Applicaster Zapp Platform. You can use this example project as a reference to build and test your own full screen plugin.

# Prepare App for local building 

#### Set Up Bintray Variables
Bintray credentials enable the build to pull Applicaster SDK dependencies from Maven.

1. Add `MAVEN_USERNAME` and `MAVEN_PASSWORD` entries to your environment variables.
2. Contact an Applicaster developer to obtain credentials for the above entries.

# Project Structure 

### App Module

Contains a really basic mock implementation of how the Zapp Platform would handle the defined UrlSchema to be handled by the plugin.

*This portion of the code facilitates testing and should only be modified to change the UrlSchema pattern you want your plugin to handle.*

### TestFullScreenPlugin-Android Module

This module contains a sample of a full screen plugin in the following classes:

* **PluginSchemeHandler.java**:
    * A simple plugin schema handler which implements the `PluginSchemeI`.
    * Contains the code to handle the properties extracted from the UrlSchema provided to the App.
    * Contains methods to initialize fullscreen view.

* **FullScreenActivity.java**:
    * Contains the simples form of an Activity to showcase how to launch it from a URlSchema.

# Getting Started

1. Create a new module that will replace the `testfullscreenplugin-android` module and simply copy/paste the handler.
2. Modify the handler to make sure it complies with the properties setup in your UrlSchema.
3. Remember that you can setup as many custom parameters as you need after the type (in the sample case we defined a custom param called `action`)
4. Continue to the deployment documentation (TBD)
