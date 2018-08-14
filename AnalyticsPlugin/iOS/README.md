# ZappAnalyticsPluginExample-iOS

The ZappAnalyticsPluginExample-iOS is an example project for creating a analytics provider plugin for the Applicaster Zapp Platform. You can use this example project as a reference for how to build your own analytics provider plugin.

If you are not familiar with Zapp please visit [our website](http://applicaster.com/?page=product) for more details.

The full [Zapp](http://zapp.applicaster.com) plugins documentation is available [here](https://developer-zapp.applicaster.com).

## Getting Started
Clone this project `$ git clone https://github.com/applicaster/zapp-plugins-examples`.

CD into `zapp-plugins-examples/Analytics/iOS` 

Run `$ pod install` in order to set the workspace.

Open `ZappAnalyticsPluginExample-iOS.xcworkspace` with Xcode 8.3.3 or above.

## Zapp Analytics Provider API
The Zapp analytics plugin API enables developers to integrate different analytic providers (i.e Google Analytics, Firebase, etc) to the the Zapp Platform.

The API contains the `ZPAnalyticsProvider` protocol which should be implemented by your provider.

In order to access the `ZPAnalyticsProvider`, you will need to import `ApplicasterSDK` and the `ZappPlugins` frameworks, for example:

```
import Foundation
import ZappPlugins
import ApplicasterSDK
import AVKit
```

### ZPAnalyticsProvider

#### Configuration 
```//MARK: Configuration
    
    /**
     Configures the provider, here is where the analytics provider should be configured.
     The custom configs from the manifest are extracted and available in `self.providedProperties` variable.
     If the configuration of the provider is successful return true otherwise return false
    */
    open override func configureProvider() -> Bool {
        
        /**
        You can access the custom config passed in the plugin_manifest by accessing self.providedProperties:
        if let value = self.providerProperties["some_custom_config_key"] as? String {
            customConfigValue = value
        }
        */
        
        return true
    }
    
    // Returns the provider key (a unique identifier for your provider)
    open override func getKey() -> String {
        return "TestAnalyticsProvider"
    }
```

#### Tracking functions
```// Tracking url params if implemented on one of the plugins
    open override func trackCampaignParamsFromUrl(_ url: NSURL) {
        super.trackCampaignParamsFromUrl(url)
    }
    
    
    //MARK: Track event Functions
    open override func trackEvent(_ eventName: String) {
        trackEvent(eventName, parameters: [:])
    }
    
    open override func trackEvent(_ eventName: String, parameters: [String : NSObject]) {
        super.trackEvent(eventName, message: message, parameters: parameters)
    }
    
    open override func trackEvent(_ eventName: String, message: String, error: NSError) {
        super.trackEvent(eventName, message: message, error: error)
    }
    
    open override func trackEvent(_ eventName: String, message: String, exception: NSException) {
        super.trackEvent(eventName, message: message, exception: exception)
    }
    
    open override func trackEvent(_ eventName: String, action: String, label: String, value: Int) {
        super.trackEvent(eventName, action: action, label: label, value: value)
    }
    
    open override func trackEvent(_ eventName: String, parameters: [String : NSObject], completion: ((Bool, String?) -> Void)?) {
        super.trackEvent(eventName, parameters: parameters, completion: completion)
    }
    
    open override func trackEvent(_ eventName: String, timed: Bool) {
        super.trackEvent(eventName, timed: timed)
    }
    
    open override func trackEvent(_ eventName: String, parameters: [String : NSObject], timed: Bool) {
        super.trackEvent(eventName, parameters: parameters, timed: timed)
    }
    
    open override func endTimedEvent(_ eventName: String, parameters: [String : NSObject]) {
        super.endTimedEvent(eventName, parameters: parameters)
    }
    
    open override func trackError(_ errorID: String, message: String, error: NSError) {
        super.trackError(errorID, message: message, error: error)
    }
    
    open override func trackError(_ errorID: String, message: String, exception: NSException) {
        super.trackError(errorID, message: message, exception: exception)
    }
    
    open override func trackScreenView(_ screenName: String, parameters: [String : NSObject]) {
        super.trackScreenView(screenName, parameters: parameters)
    }
    
    //MARK: Push Notification Token
    
    /**
     sets the Push Notification deviceToken
     */
    open override func setPushNotificationDeviceToken(_ deviceToken: Data) {
        super.setPushNotificationDeviceToken(deviceToken)
    }
```
    

#### Using the Zapp Plugin Configuration JSON
When creating a plugin in Zapp we can create custom configuration fields. This enable the Zapp user to fill relevant details for the specific plugin. More details can found in the [Zapp Plugin Manifest Format](http://zapp-tech-book.herokuapp.com/zappifest/plugins-manifest-format.html).
You can use these configuration in the configureProvider function like so:

```
if let value = self.providerProperties["some_custom_config_key"] as? String {
    customConfigValue = value
}
```

## Debug
In order to be able to test and debug your project A->Z, you will need add your plugin to the `zapp-ios-plugins-starter-kit`, which can be cloned [here](https://github.com/applicaster/zapp-ios-plugins-starter-kit).

## TO-DOs
- [ ] readme improvements:
    - add details about the `zapp-ios-plugins-starter-kit`
