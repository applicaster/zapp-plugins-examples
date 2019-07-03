//
//  SampleGeneralPlugin.swift
//  CleengPluginExample
//
//  Created by Liviu Romascanu on 30/05/2018.
//  Copyright © 2018 Applicaster. All rights reserved.
//

import Foundation
import ZappPlugins
import ZappGeneralPluginsSDK

@objc public class SampleGeneralPlugin: ZPGeneralBaseProvider, ZPAppLoadingHookProtocol {
    
    // MARK: - ZPAdapterProtocol implementation
    /**
        Implement this method if you need the plugin to respond to a URL scheme.
        The parameteters of the URL scheme will be passed as a dictionary to be handled by the plugin.
     
        Since this function can be called directly to a plugin by name or by type - please include and verify that the URL scheme should be indeed handled.
     
        Best practices suggestions for this:
        * Use "action" parameter in the URL Scheme
        * Use of further custom parameters in the URL scheme
     
        Note: A "pluginType": "general" field will be populated
     
        In order to display something on top of the current app - please use "ZAAppConnector.sharedInstance().navigationDelegate.topmostModal()"
     */
    @objc public func handleUrlScheme(_ params: NSDictionary) {
        
    }
    
    // MARK: - ZPAdapterProtocol implementation
    /*
     This protocol should be implemented by plugin that need to add some logic before application load data and before rootViewController presented. example: login plugin will implement this inteface in order to make login flow before application data launched.
     
     All the methods in this protocol are optional, implement only the necessary ones.
     */
    
    /*
     This method called after Plugins loaded locally, but the account load failed
     */
    @objc public func executeOnFailedLoading(completion: (() -> Void)?) {
        //Write you implementation here
        
        //Release the hook
        completion?()
    }
    
    /*
     This method called after Plugins loaded, and also after initial account data retrieved, you can add logic that not related to the application data.
     */
    @objc public func executeOnLaunch(completion: (() -> Void)?) {
        //Write you implementation here
        
        //Release the hook
        completion?()
    }
    
    /*
     This method called after all the data loaded and before viewController presented.
     */
    @objc public func executeOnApplicationReady(displayViewController: UIViewController?, completion: (() -> Void)?) {
        //Write you implementation here
        
        //Release the hook
        completion?()
    }
    
    /*
     This method called after viewController is presented.
     */
    @objc public func executeAfterAppRootPresentation(displayViewController: UIViewController?, completion: (() -> Void)?) {
        //Write you implementation here
        
        //Release the hook
        completion?()
    }
    
    /*
     This method called when the application:continueUserActivity:restorationHandler is called.
     */
    @objc public func executeOnContinuingUserActivity(_ userActivity: NSUserActivity?, completion: (() -> Void)?) {
        //Write you implementation here
        
        //Release the hook
        completion?()
    }
}
