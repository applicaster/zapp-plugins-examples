//
//  AdViewProtocolController.swift
//  ZappAdvertisementPluginExample-iOS
//
//  Created by Pablo Rueda on 27/09/2018.
//  Copyright © 2018 Applicaster. All rights reserved.
//

import UIKit
import ZappPlugins
import MyAdvertisementPlugin

class AdViewProtocolController: ZPAdViewProtocol {
    
    var adLoadedClosure: (UIView?) -> ()
    
    //MARK: - Lifecycle
    
    init(adLoadedClosure: @escaping (UIView?) -> ()) {
        self.adLoadedClosure = adLoadedClosure
    }
    
    //MARK: - ZPAdViewProtocol
    
    func adLoaded(view: UIView?) {
        adLoadedClosure(view)
    }
    
    func stateChanged(adViewState: ZPAdViewState) {
        switch adViewState {
        case .impressed:
            print("Ad impressed")
            
        case .clicked:
            print("Ad clicked")
            
        case .closed:
            print("Ad closed")
            
        default:
            break
        }
    }
    
    func adLoadFailed(error: Error) {
        print("Error: \(error.localizedDescription)")
    }
}

