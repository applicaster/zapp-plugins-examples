//
//  ZappAnalyticsProvider.swift
//  ZappAnalyticsPluginExample-iOS
//
//  Created by Javier Casaudoumecq on 8/8/18.
//  Copyright © 2018 Javier Casaudoumecq. All rights reserved.
//

import Foundation
import ZappPlugins
import ApplicasterSDK

open class TestAnalyticsProvider: ZPAnalyticsProvider {
    
    let ACCOUNT_ID = "mobile_app_account_id"
    
    var accountId: String = ""
    
    open override func getKey() -> String {
        return "ZappAnalyticsProvider"
    }
    
    open override func configureProvider() -> Bool {
        if let value = self.providerProperties[ACCOUNT_ID] as? String {
            accountId = value
        }
        return true
    }
    
    open override func createAnalyticsProvider(_ allProvidersSetting: [String : NSObject]) -> Bool {
        guard let gai = GAI.sharedInstance() else {
            assert(false, "Google Analytics not configured correctly")
        }
        gai.tracker(withTrackingId: "YOUR_TRACKING_ID")
        // Optional: automatically report uncaught exceptions.
        gai.trackUncaughtExceptions = true
        
        // Optional: set Logger to VERBOSE for debug information.
        // Remove before app release.
        gai.logger.logLevel = .verbose
        
        return true
    }
}
