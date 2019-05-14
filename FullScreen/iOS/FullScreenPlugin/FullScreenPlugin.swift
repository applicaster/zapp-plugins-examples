//
//  FullScreenPlugin.swift
//  FullScreenPlugin
//
//  Created by Liviu Romascanu on 14/05/2019.
//

import UIKit
import ZappPlugins

public class FullScreenPlugin: NSObject, ZPPluggableScreenProtocol {
    var pluginModel: ZPPluginModel?
    var screenModel: ZLScreenModel?
    var dataSourceModel: NSObject?
    
    required public init?(pluginModel: ZPPluginModel, screenModel: ZLScreenModel, dataSourceModel: NSObject?) {
        super.init()
        self.pluginModel = pluginModel
        self.screenModel = screenModel
        self.dataSourceModel = dataSourceModel
    }
    
    public var screenPluginDelegate: ZPPlugableScreenDelegate?
    
    public func createScreen() -> UIViewController {
        let viewController = FullScreenPluginViewController.init(nibName: nil, bundle: Bundle(for: FullScreenPluginViewController.self))        
        return viewController
    }

}
