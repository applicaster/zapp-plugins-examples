# Analytics Provider Plugin Example - Android
This is an example project for creating an Analytics Provider Plugin for the Applicaster Zapp Platform. You can use this example project as a reference to build and test your own Analytics Provider Plugin.

# Prepare app for local building
#### Set Up Bintray Variables
Bintray credentials enable the build to pull Applicaster SDK dependencies from Maven.

1. Add `MAVEN_USERNAME` and `MAVEN_PASSWORD` entries to your environment variables.
2. Contact an Applicaster developer to obtain credentials for the above entries.

# Project Structure

### App Module

Contains mock implementation of how the Zapp Platform log events to the Analytics providers that are registered in the `plugin_configurations.json`.

*This portion of the code facilitates testing and should only be modified to change the events you need to log to your analytics provider.*

### TestAnalyticsPlugin-Android Module

This module contains a sample analytics using Google Analytics as the provider in the following class:

* **TestAnalyticsAgent.java**:
    * Extends `BaseAnalyticsAgent`.
    * Contains the initialization of the plugin and the plugin configurations.
    * Contains implementation of logging events using Google Analytics Tracker.

# Getting Started

1. Create a new module that will replace the `TestAnalyticsPlugin-Android` module. It should contain a class which extends `BaseAnalyticsAgent`. You can use `TestAnalyticsAgent.java` as a template.
2. Modify the `plugin_configurations.json` to adapt to your Analytics provider. Add any necessary configurations to the manifest. More info about the manifest can be found [here](http://zapp-tech-book.herokuapp.com/zappifest/plugins-manifest-format.html)
3. Modify the app module's `MainActivity` to pass the required events.
4. Make sure the Analytics provider is getting all the desired events.

# Help!
If you have any question please contact [Juan Hernandez](mailto:j.hernandez@applicaster.com).