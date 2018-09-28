//
//  ViewController.swift
//  ZappAdvertisementPluginExample-iOS
//
//  Created by Pablo Rueda on 26/09/2018.
//  Copyright © 2018 Applicaster. All rights reserved.
//

import UIKit
import ZappPlugins
import MyAdvertisementPlugin

class ViewController: UIViewController {
    //#warning("Please complete the fields")
    struct Constants {
        static let interstitialAdUnitId = ""
        static let screenBannerAdUnitId = ""
        static let inlineBannerAdUnitId = ""
    }
    
    @IBOutlet weak var screenBannerContainerView: UIView!
    @IBOutlet weak var inlineBannerContainerView: UIView!
    @IBOutlet weak var inlineBoxBannerContainerView: UIView!
    @IBOutlet weak var inlineSmartBannerContainerView: UIView!
    
    @IBOutlet weak var inlineBannerHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var inlineBoxBannerHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var inlineSmartBannerHeightConstraint: NSLayoutConstraint!
    
    var interstitialPresenter: ZPAdPresenterProtocol?
    var screenBannerPresenter: ZPAdPresenterProtocol?
    var inlineBannerPresenter: ZPAdPresenterProtocol?
    var inlineBoxBannerPresenter: ZPAdPresenterProtocol?
    var inlineSmartBannerPresenter: ZPAdPresenterProtocol?
    var interstitialWasPresented = false
    
    //MARK: - Lifecycle

    override func viewDidLoad() {
        super.viewDidLoad()
        
        let adPlugin = MyAdvertisementPlugin()
        
        //Screen banner
        let screenBannerAdViewProtocol = AdViewProtocolController { (view) in
            self.screenBannerContainerView.addSubview(view!)
        }
        let screenBannerConfig = ZPAdConfig(adUnitId: Constants.screenBannerAdUnitId, adType: .screenBanner)
        screenBannerPresenter = adPlugin.createAdPresenter(adView: screenBannerAdViewProtocol, parentVC: self)
        screenBannerPresenter?.load(adConfig: screenBannerConfig)
        
        //Inline banner
        let inlineBannerHeight = adPlugin.size(forInlineBannerSize: "BANNER").height
        inlineBannerHeightConstraint.constant = inlineBannerHeight
        let inlineBannerAdViewProtocol = AdViewProtocolController { (view) in
            self.inlineBannerContainerView.addSubview(view!)
        }
        let inlineConfig = ZPAdConfig(adUnitId: Constants.inlineBannerAdUnitId, inlineBannerSize:"BANNER")
        inlineBannerPresenter = adPlugin.createAdPresenter(adView: inlineBannerAdViewProtocol, parentVC: self)
        inlineBannerPresenter?.load(adConfig: inlineConfig)
        
        //Box inline banner
        let inlineBoxBannerHeight = adPlugin.size(forInlineBannerSize: "BOX_BANNER").height
        inlineBoxBannerHeightConstraint.constant = inlineBoxBannerHeight
        let inlineBoxBannerAdViewProtocol = AdViewProtocolController { (view) in
            self.inlineBoxBannerContainerView.addSubview(view!)
        }
        let boxInlineConfig = ZPAdConfig(adUnitId: Constants.inlineBannerAdUnitId, inlineBannerSize:"BOX_BANNER")
        inlineBoxBannerPresenter = adPlugin.createAdPresenter(adView: inlineBoxBannerAdViewProtocol, parentVC: self)
        inlineBoxBannerPresenter?.load(adConfig: boxInlineConfig)
        
        //Smart inline banner
        let inlineSmartBannerHeight = adPlugin.size(forInlineBannerSize: "SMART_BANNER").height
        inlineSmartBannerHeightConstraint.constant = inlineSmartBannerHeight
        let smartInlineBoxBannerAdViewProtocol = AdViewProtocolController { (view) in
            self.inlineSmartBannerContainerView.addSubview(view!)
        }
        let smartInlineConfig = ZPAdConfig(adUnitId: Constants.inlineBannerAdUnitId, inlineBannerSize:"SMART_BANNER")
        inlineSmartBannerPresenter = adPlugin.createAdPresenter(adView: smartInlineBoxBannerAdViewProtocol, parentVC: self)
        inlineSmartBannerPresenter?.load(adConfig: smartInlineConfig)
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        
        if !interstitialWasPresented {
            let adPlugin = MyAdvertisementPlugin()
            
            //Interstitial
            let interstitialAdViewProtocol = AdViewProtocolController { (view) in
                self.interstitialPresenter?.showInterstitial()
                self.interstitialWasPresented = true
            }
            let interstitialConfig = ZPAdConfig(adUnitId: Constants.interstitialAdUnitId, adType: .interstitial)
            interstitialPresenter = adPlugin.createAdPresenter(adView: interstitialAdViewProtocol, parentVC: self)
            interstitialPresenter?.load(adConfig: interstitialConfig)
        }
    }
}

