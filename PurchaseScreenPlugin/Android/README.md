# Purchase Screen Plugin Example - Android
This is an example project for creating an Purchase Screen Plugin for the Applicaster Zapp Platform. You can use this example project as a reference to build and test your own Purchase Screen Plugin.

# Prepare app for local building
#### Set Up Bintray Variables
Bintray credentials enable the build to pull Applicaster SDK dependencies from Maven.

1. Add `MAVEN_USERNAME` and `MAVEN_PASSWORD` entries to your environment variables.
2. Contact an Applicaster developer to obtain credentials for the above entries.

# Project Structure

### App Module

This project contains a [ScreenPlugin](https://developer-zapp.applicaster.com/ui-builder/android/ScreenPlugin.html) sample on how to use [Applicaster IAP Framework](https://github.com/applicaster/applicaster-iap-framework) as in-app purchases library.

The class `IAPPlugin` implements `PluginScreen` class contains the initialization of the plugin and implements two methods: `generateFragment(...)` and `present(...)`. Also project includes view and presenter parts with classes for displaying purchase items and managing purchases.

# Getting Started

1. Create a new module that will replace the `iaptestplugin` module. It should contain a class which implements `PluginScreen`. You can use `IAPPlugin.kt` as a template.
2. Modify the `plugin_configurations.json` to adapt to your IAP test screen plugin. Add any necessary configurations to the manifest. More info about the manifest can be found [here](http://zapp-tech-book.herokuapp.com/zappifest/plugins-manifest-format.html)
3. You can use `ItemsInfo` class to pas to it purchase items ID and `SkuType` and set it intent extras to catch it in started activity. This logic can be changed by developer.