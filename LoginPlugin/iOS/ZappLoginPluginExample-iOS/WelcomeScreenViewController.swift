//
//  WelcomeScreenViewController.swift
//  ZappLoginPluginExample-iOS
//
//  Created by Roi Kedarya on 21/05/2018.
//  Copyright © 2018 Applicaster. All rights reserved.
//

import UIKit
import ZappPlugins

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
