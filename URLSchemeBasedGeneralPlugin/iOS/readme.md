# General Plugin example - iOS

This project serves as a base example on implementing a general plugin for iOS - with focus on using URL schemes in order to launch activities.

This example plugin implements the following 2 protocols that can be found in Zapp plugins repository:
* `ZPGeneralPluginProtocol`
* `ZPAdapterProtocol`

Inheriting from the `ZPGeneralBaseProvider` simplifies the initialization process, handling of the configuration json dictionary handed over when initializing the plugin etc.

In this example the only implemented call is `func handleUrlScheme(_ params: NSDictionary)` which is used to handle calls for the URL scheme.

## Project setup
* Run `pod update` in the main library
* Open `ZappGeneralPluginExample-iOS.xcWorkspace` with Xcode 10.
* Hit run

There are 2 buttons:
1. Initialize plugin with configuration - Use the textfield to pass a valid JSON for configurations that will be used to initialize the plugin.
2. Handle URL scheme - use the textfield to pass a valid json of properties to the plugin.

## Anatomy of a Zapp Plugin

A zapp plugin is comprised of a couple of components:
1. podspec - All plugins are distributed in a form of a cocoapod.
2. plugin-manifest.json - This file is used to generate a plugin configuration on the zapp backend. Please note the option to define custom keys in the supplied sample manifest (`plugin-manifest.json`). Those keys can be customized by app version and come in the configuration object on plugin initialization.
3. Code files
4. Assets

Please note - since the entire distribution of this plugin is in a form of a framework built from a podspec - all assets should be pulled from the plugin bundle and not from the main bundle.

## Getting access to helper tools
Through using the `ZAAppConnector.sharedInstance()` a plugin can access various parts of the app through predefined delegates

A few notable ones are:
* `layoutsStylesDelegate` - Used to configure various UI elements according to styles from the Zapp system
* `localizationDelegate` - Used to grab localizations for various texts from the app
* `navigationDelegate` - Used to get topmost view controller , navigate to home screen and getting the navigation manager
* `analyticsDelegate` - Used to send analytics events for screen views or actions

## Plugin protocols
* `ZPAdapterProtocol` - Base component protocol that includes the initialization of a plugin , configuration json object and handle url scheme calls.
* `ZPGeneralPluginProtocol` - Base plugin protocol that includes the standard calls for activation and deactivation
* `ZPAppLoadingHookProtocol` - A protocol that can be implemented to hook into the app loading to do processing when the app loads. Please consult with Applicaster regarding this.
* `ZPGeneralPluginUIProtocol` - A protocol for a plugin to provide a view controller to be handled by Zapp. Please consult with Applicaster regarding this.


## React native
If the plugin intends of using React Native - please contact Applicaster - this type of plugin is meant to use native code only.
React has already a separate set of hooks and initializations to really simplify integrating it.