//
//  ViewController.swift
//  ZappAnalyticsPluginExample-iOS
//
//  Created by Javier Casaudoumecq on 8/8/18.
//  Copyright © 2018 Javier Casaudoumecq. All rights reserved.
//

import UIKit
import ApplicasterSDK

class ViewController: UIViewController {
    
    let CLICKING_BUTTON_1 = "Clicking button 1";
    let CLICKING_BUTTON_2 = "Clicking button 2";
    let EVENT_WITH_PARAMS = "Action with parameters";
    let ERROR_EVENT = "Error Event";
    let SCREEN_NAME = "Home Screen";

    @IBOutlet var eventButtons: [UIButton]!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Ignore this portion of the flow, this will happen automatically in the Applicaster App initilization flow
        let provider = TestAnalyticsProvider.init()
        if provider.configureProvider() {
            APAnalyticsManager.start(withSampleProvider: provider)
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func eventClicked(_ sender: UIButton) {
        /**
        This simulates various events that might happen in the app. Use this to see how the TestAnalyticsProvider reacts
        to these events.
        */
        switch sender.tag {
        case 1:
            APAnalyticsManager.trackEvent(CLICKING_BUTTON_1)
        case 2:
            APAnalyticsManager.trackEvent(CLICKING_BUTTON_2)
        case 3:
            APAnalyticsManager.trackEvent(EVENT_WITH_PARAMS, withParameters: ["categoryTapped":"Movies"])
        case 4:
            APAnalyticsManager.trackError(ERROR_EVENT, message: "Error occurred", error: nil)
        case 5:
            APAnalyticsManager.trackScreenView(SCREEN_NAME)
        default:
            APAnalyticsManager.trackEvent(CLICKING_BUTTON_1)
        }
    }
    
}

