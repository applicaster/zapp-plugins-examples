//
//  ZappAnalyticsProvider.swift
//  ZappAnalyticsPluginExample-iOS
//
//  Created by Javier Casaudoumecq on 8/8/18.
//  Copyright © 2018 Javier Casaudoumecq. All rights reserved.
//

import Foundation
import ZappPlugins

open class TestAnalyticsProvider: NSObject, ZPAnalyticsProviderProtocol {
    let ACCOUNT_ID = "mobile_app_account_id"
    
    var accountId: String = ""
    var tracker : GAITracker?
    
    //MARK: Configuration
    public var configurationJSON: NSDictionary?
    
    public required override init() {
        
    }
    
    public required init(configurationJSON: NSDictionary?) {
        self.configurationJSON = configurationJSON
    }
    
    /**
     Configures the provider, here is where the analytics provider should be configured.
     The custom configs from the manifest are extracted and available in `self.providedProperties` variable.
     If the configuration of the provider is successful return true otherwise retrun false
    */
    open func configureProvider() -> Bool {
        
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
    open func getKey() -> String {
        return "TestAnalyticsProvider"
    }
    
    // Tracking url params if implemented on one of the plugins
    open func trackCampaignParamsFromUrl(_ url: NSURL) {
        //super.trackCampaignParamsFromUrl(url)
    }
    
    
    //MARK: Track event Functions
    open func trackEvent(_ eventName: String) {
        trackEvent(eventName, parameters: [:])
    }
    
    open func trackEvent(_ eventName: String, parameters: [String : NSObject]) {
        if let tracker = tracker {
            tracker.send(parameters)
        }
    }
    
    open func trackEvent(_ eventName: String, message: String, error: NSError) {
        //super.trackEvent(eventName, message: message, error: error)
    }
    
    open func trackEvent(_ eventName: String, message: String, exception: NSException) {
        //super.trackEvent(eventName, message: message, exception: exception)
    }
    
    open func trackEvent(_ eventName: String, action: String, label: String, value: Int) {
        //super.trackEvent(eventName, action: action, label: label, value: value)
    }
    
    open func trackEvent(_ eventName: String, parameters: [String : NSObject], completion: ((Bool, String?) -> Void)?) {
        //super.trackEvent(eventName, parameters: parameters, completion: completion)
    }
    
    open func trackEvent(_ eventName: String, timed: Bool) {
        //super.trackEvent(eventName, timed: timed)
    }
    
    open func trackEvent(_ eventName: String, parameters: [String : NSObject], timed: Bool) {
        //super.trackEvent(eventName, parameters: parameters, timed: timed)
    }
    
    open func endTimedEvent(_ eventName: String, parameters: [String : NSObject]) {
        //super.endTimedEvent(eventName, parameters: parameters)
    }
    
    open func trackError(_ errorID: String, message: String, error: NSError) {
        if let tracker = tracker {
            tracker.send([errorID : message])
        }
    }
    
    open func trackError(_ errorID: String, message: String, exception: NSException) {
        //super.trackError(errorID, message: message, exception: exception)
    }
    
    open func trackScreenView(_ screenName: String, parameters: [String : NSObject]) {
        if let tracker = tracker {
            tracker.set(kGAIScreenName, value: screenName)
            tracker.send(GAIDictionaryBuilder.createScreenView().build() as! [AnyHashable : Any]?)
        }
    }
    
    //MARK: Push Notification Token
    
    /**
     sets the Push Notification deviceToken
     */
    open func setPushNotificationDeviceToken(_ deviceToken: Data) {
        //super.setPushNotificationDeviceToken(deviceToken)
    }
    
    // MARK: Extra Analytics methods

    public func analyticsMaxParametersAllowed() -> Int {
        return 1
    }
    
    public func setBaseParameter(_ value: NSObject?, forKey key: String) {
        
    }
    
    public func sortPropertiesAlphabeticallyAndCutThemByLimitation(_ properties: [String : NSObject]) -> [String : NSObject] {
        return properties
    }
    
    public func createAnalyticsProvider(_ allProvidersSetting: [String : NSObject]) -> Bool {
        return true
    }
    
    public func updateGenericUserProperties(_ genericUserProfile: [String : NSObject]) {
        
    }
    
    public func updateDefaultEventProperties(_ eventProperties: [String : NSObject]) {
        
    }
    
    public func trackScreenView(_ screenName: String, parameters: [String : NSObject], completion: ((Bool, String?) -> Void)?) {
        
    }
    
    public func presentToastForLoggedEvent(_ eventDescription: String?) {
        
    }
    
    public func canPresentToastForLoggedEvents() -> Bool {
        return false
    }
    
    public func shouldTrackEvent(_ eventName: String) -> Bool {
        return true
    }
}
