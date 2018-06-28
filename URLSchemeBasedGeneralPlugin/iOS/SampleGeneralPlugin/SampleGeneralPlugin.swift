//
//  SampleGeneralPlugin.swift
//  CleengPluginExample
//
//  Created by Liviu Romascanu on 30/05/2018.
//  Copyright © 2018 Applicaster. All rights reserved.
//

import Foundation
import ZappPlugins

@objc public class SampleGeneralPlugin: ZPGeneralBaseProvider {
    
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
}
