//
//  WelcomeScreenViewController.swift
//  ZappLoginPluginExample-iOS
//
//  Created by Roi Kedarya on 21/05/2018.
//  Copyright © 2018 Applicaster. All rights reserved.
//

import UIKit
import ZappPlugins

/**
 This VC represent the last phase after we logged in or if we are already logged,
 it contains a logout screen that simulates how the login plugin is invokes from the zapp side if a logout request is beeing sent
 in other cases a logout can be called in other ways like a url request of this you can also implement the ZPAdapterProtocol
 and implement public func handleUrlScheme(_ params:NSDictionary) method in order to cath the url
 
 */
class WelcomeScreenViewController: UIViewController {

    @IBOutlet weak var logoutButton: UIButton!
    var loginPlugin:ZPLoginProviderUserDataProtocol?

    @IBAction func logoutButtonTapped(_ sender: UIButton) {
        let weakself = self
        if let loginPlugin = weakself.loginPlugin as? FacebookLoginPlugin {
            loginPlugin.logout { (status) in
                loginPlugin.setUserToken(token: "")
                loginPlugin.setUserExpirationDate(token: nil)
                loginPlugin.deleteCookies()
                let alertView = UIAlertController.init(title: "", message: "You are Logged-out", preferredStyle: .alert)
                alertView.addAction(UIAlertAction.init(title: "O.K", style: .default, handler: { (_) in
                    weakself.dismiss(animated: true, completion: nil)
                }))
                weakself.present(alertView, animated: true, completion: nil)
            }
        }
    }
    
    @IBAction func backButtonPressed(_ sender: UIBarButtonItem) {
        self.dismiss(animated: true, completion: nil)
    }
}
