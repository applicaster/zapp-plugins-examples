//
//  FacebookInfoViewController.swift
//  ZappLoginPlugins
//
//  Created by Roi Kedarya on 17/05/2018.
//  Copyright © 2018 Applicaster. All rights reserved.
//

import Foundation
import ZappPlugins
/**
 This VC manages the login process and coresponds with the login plugin,
 for example when a user click the login button a login request is sent to the login plugin, and a result is beeing deliverd back
    FacebookInfoViewController decides if we go to login screen or, if the user is already verified, enter the welcome screen
 */
@objc class FacebookInfoViewController: UIViewController {
    
    @IBOutlet weak var fbButton: UIButton!
    var loginPlugin:ZPLoginProviderUserDataProtocol?
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override open var supportedInterfaceOrientations: UIInterfaceOrientationMask {
        return UIDevice.current.userInterfaceIdiom == .pad ? .all : .portrait
    }
    
    func goToWelcomeScreen() {
        if let vc = self.storyboard?.instantiateViewController(withIdentifier: "welcomeScreen") as? WelcomeScreenViewController {
            vc.loginPlugin = self.loginPlugin
            self.present(vc, animated: true, completion: nil)
        }
    }
    
    @IBAction func loginButtonClicked(_ sender: UIButton) {
        if let loginPlugin = loginPlugin {
            if loginPlugin.isAuthenticated() {
                self.goToWelcomeScreen()
            } else {
                let weakself = self
                loginPlugin.login(nil, completion: { (loginStatus) in
                    if loginStatus == .completedSuccessfully {
                        weakself.goToWelcomeScreen()
                    } else {
                        let alertView = UIAlertController.init(title: "", message: loginStatus.logoutDescriptionDefaultValue(), preferredStyle: .alert)
                        alertView.addAction(UIAlertAction.init(title: "O.K", style: .default, handler: nil))
                        weakself.present(alertView, animated: true, completion: nil)
                    }
                })
            }
        } else {
            let alert = UIAlertController.init(title: "", message: "logout Failed", preferredStyle: .alert)
            alert.addAction(UIAlertAction.init(title: "O.K", style: .default, handler: nil))
            self.present(alert, animated: true, completion: nil)
        }
    }
}
