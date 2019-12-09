//
//  SelectionScreenViewController.swift
//  ZappLoginPluginExample-iOS
//
//  Created by Roi Kedarya on 17/05/2018.
//  Copyright © 2018 Applicaster. All rights reserved.
//

import Foundation
import UIKit
import ZappPlugins

/*
 This Vc constist from a list of login plugins ,it starts the app flow form fist phase of selecting and initializing the selected,
 same way zappApp invokes the login plugin 
 **/
@objc class SelectionScreenViewController: UIViewController {
    
    @IBOutlet weak var facebookLogin: UIButton!
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let loginPlugin = ZAAppConnector.sharedInstance().pluginsDelegate?.loginPluginsManager?.createWithUserData(),
            let extensions = [:] as? [String : NSObject] {
            if let isUserComply = loginPlugin.isUserComply?(policies: extensions),
                isUserComply == true {
                //show welcome screen
                if let vc = self.storyboard?.instantiateViewController(withIdentifier:"welcomeScreen") as? WelcomeScreenViewController {
                    vc.loginPlugin = loginPlugin
                    segue.source.present(vc, animated: true, completion: nil)
                }
            } else {
                //show login screen
                guard let facebookInfoVC = segue.destination as? FacebookInfoViewController else { return }
                facebookInfoVC.loginPlugin = loginPlugin
            }
        }
    }
}

