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
        let provider = TestAnalyticsProvider()
        if provider.configureProvider() {
            ZAAppConnector.sharedInstance().analyticsDelegate.startManager(withAccountId: nil)
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
            ZAAppConnector.sharedInstance().analyticsDelegate.trackEvent(name: CLICKING_BUTTON_1, parameters: nil, timed: false)
        case 2:
            ZAAppConnector.sharedInstance().analyticsDelegate.trackEvent(name: CLICKING_BUTTON_2, parameters: nil, timed: false)
        case 3:
            ZAAppConnector.sharedInstance().analyticsDelegate.trackEvent(name: EVENT_WITH_PARAMS, parameters: ["categoryTapped":"Movies"], timed: false)
        case 4:
            ZAAppConnector.sharedInstance().analyticsDelegate.trackEvent(name: ERROR_EVENT, parameters: ["message": "Error occurred"], timed: false)
        case 5:
            ZAAppConnector.sharedInstance().analyticsDelegate.trackScreenView(withModelTitle: SCREEN_NAME)
        default:
            ZAAppConnector.sharedInstance().analyticsDelegate.trackEvent(name: CLICKING_BUTTON_1, parameters: nil, timed: false)
        }
    }
    
}

