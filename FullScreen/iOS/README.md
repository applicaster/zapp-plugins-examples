# Zapp Full Screen Plugin Example - iOS

This is an example project for creating a full screen plugin for the Applicaster Zapp Platform. You can use this example project as a reference to build and test your own full screen plugin.

For the full documentation, [Click Here](https://developer.applicaster.com/ui-builder/ios/ScreenPlugin.html)

## Pre-requirements

Please make sure you use:
* Xcode 10.2.1
* cocoapods 1.7.5

## Setup and build project locally

* run `pod update`
* Open workspace
* Run the `FullScreenPluginExample` target

The first screen presented is a general screen with a button.
If you click the button - the full screen plugin will be displayed with a push action in the current navigation controller.

## Project Structure 

This project consists of the following
* `FullScreenPluginExample` Container project
* `Podfile` Example podfile, setting `ZappPlugin` and `FullScreenPlugin` as dependancies.
* `FullScreenPlugin` Directory containing implementation of the sample plugin
* `FullScreenPlugin.podspec` Sample podspec for the plugin

Note: In this example the plugin configuration, model and datasource are either simplified or empty.
Please refer to the full documentation for the relevant structures and APIs (such as plugin model configuration fields, screen model fields and datasources).