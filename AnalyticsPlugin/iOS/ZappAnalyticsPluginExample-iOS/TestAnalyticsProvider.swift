//
//  ZappAnalyticsProvider.swift
//  ZappAnalyticsPluginExample-iOS
//
//  Created by Javier Casaudoumecq on 8/8/18.
//  Copyright © 2018 Javier Casaudoumecq. All rights reserved.
//

import Foundation
import ZappAnalyticsPluginsSDK

open class TestAnalyticsProvider: ZPAnalyticsProvider {
    
    let ACCOUNT_ID = "mobile_app_account_id"
    
    var accountId: String = ""
    var tracker : GAITracker?
    
    //MARK: Configuration
    
    /**
     Configures the provider, here is where the analytics provider should be configured.
     The custom configs from the manifest are extracted and available in `self.providedProperties` variable.
     If the configuration of the provider is successful return true otherwise retrun false
    */
    open override func configureProvider() -> Bool {
        
        /**
        You can access the custom config passed in the plugin_manifest by accessing self.providedProperties:
        if let value = self.providerProperties["some_custom_config_key"] as? String {
            customConfigValue = value
        }
        */
        
        guard let gai = GAI.sharedInstance() else {
            assert(false, "Google Analytics not configured correctly")
        }
        
        /*
         For this example we are setting up Google Analytics with a static account,
         in production plugins you want to extract any configuration details from the
         plugin configuration by accessing the providedProperties as shown above.
        */
        accountId = "UA-120927512-1"
        tracker = gai.tracker(withTrackingId: accountId)
        gai.trackUncaughtExceptions = true
        gai.logger.logLevel = .verbose
        
        return true
    }
    
    // Returns the provider key (a unique identifier for your provider)
    open override func getKey() -> String {
        return "TestAnalyticsProvider"
    }
    
    // Tracking url params if implemented on one of the plugins
    open override func trackCampaignParamsFromUrl(_ url: NSURL) {
        super.trackCampaignParamsFromUrl(url)
    }
    
    
    //MARK: Track event Functions
    open override func trackEvent(_ eventName: String) {
        trackEvent(eventName, parameters: [:])
    }
    
    open override func trackEvent(_ eventName: String, parameters: [String : NSObject]) {
        if let tracker = tracker {
            tracker.send(parameters)
        }
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
        if let tracker = tracker {
            tracker.send([errorID : message])
        }
    }
    
    open override func trackError(_ errorID: String, message: String, exception: NSException) {
        super.trackError(errorID, message: message, exception: exception)
    }
    
    open override func trackScreenView(_ screenName: String, parameters: [String : NSObject]) {
        if let tracker = tracker {
            tracker.set(kGAIScreenName, value: screenName)
            tracker.send(GAIDictionaryBuilder.createScreenView().build() as! [AnyHashable : Any]?)
        }
    }
    
    //MARK: Push Notification Token
    
    /**
     sets the Push Notification deviceToken
     */
    open override func setPushNotificationDeviceToken(_ deviceToken: Data) {
        super.setPushNotificationDeviceToken(deviceToken)
    }
}
