//
//  PurchaseScreenPlugin.swift
//  PurchaseScreenPlugin
//
//  Created by Roman Karpievich on 4/30/19.
//  Copyright © 2019 Roman Karpievich. All rights reserved.
//

import UIKit
import ZappPlugins

class PurchaseScreenPlugin: NSObject, ZPPluggableScreenProtocol {
    
    // MARK: - ZPPluggableScreenProtocol
    
    var screenPluginDelegate: ZPPlugableScreenDelegate?
    
    required init?(pluginModel: ZPPluginModel,
                   screenModel: ZLScreenModel,
                   dataSourceModel: NSObject?) {
        super.init()
    }
    
    func createScreen() -> UIViewController {
        return PurchasesViewController.storyboardInstance()
    }
}
