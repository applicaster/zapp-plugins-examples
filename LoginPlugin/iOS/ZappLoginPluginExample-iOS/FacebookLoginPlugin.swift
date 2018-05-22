//
//  FacebookLoginPlugin.swift.swift
//  ZappLoginPlugins
//
//  Created by Roi Kedarya on 17/05/2018.
//  Copyright © 2018 Applicaster. All rights reserved.
//

import Foundation
import ZappPlugins
import ApplicasterSDK
import FacebookCore
import FacebookLogin

/*
 This is an example for a login plugin imlementation.
 This shows how to use the ZPLoginProviderProtocol and the ZPLoginProviderUserDataProtocol
 in order to create and integrate any login plugin that is needed
 in this example we choose to implemented the facebook login plugin
 This is the main class of the implementation
 **/
@objc class FacebookLoginPlugin: NSObject, ZPLoginProviderProtocol, ZPLoginProviderUserDataProtocol {
    
    public var configurationJSON: NSDictionary?
    var loginCompletion: ((ZappPlugins.ZPLoginOperationStatus) -> Swift.Void)?
    var fbToken: AccessToken?
    var loginManager:LoginManager?
    
    public required override init() {
        super.init()
    }
    
    
//MARK: ZPLoginProviderUserDataProtocol
    
    /**
     This methood is called in order to verify if we need to start a login flow with respect to the policies dictionary
     @params policies - dictionary containing policies to be considered when returning the result, if passed as nil - same as isAuthenticated() method.
     Returns bool value indicating if the user is already verified if not  we start the login proccess
     in this example we check if the login token exists and if it is valid
     */
    public func isUserComply(policies:[String: NSObject]) -> Bool {
        return self.isAuthenticated()
    }
    
    /**
     Getter to the user Token usually can be used for authentication check
    */
    public func getUserToken() -> String {
        var token = ""
        if let userToken = UserDefaults.standard.string(forKey: "fb_login_token") {
            token = userToken
        }
        return token
    }
    
    /**
     Setter for the user Token - usually set after login success
     @Params: the authentication recieved when login successfuly
     */
    public func setUserToken(token: String?) {
        UserDefaults.standard.set(token, forKey: "fb_login_token")
    }
    
//MARK: ZPLoginProviderProtocol
    
    /**
     Initialization of login Plugin instance
     @Params: configurationJSON - dictionary containing the Plugin setitngs as defined in the plugin manifest
     */
    public required init(configurationJSON: NSDictionary?) {
        super.init()
        self.loginManager = LoginManager()
        self.configurationJSON = configurationJSON
    }
    
    /**
     Test User in order to login successfuly use these credentials
     user name: jorge_mtglsje_valdano@tfbnw.net
     password: applicaster is the best
    **/
    
    /**
     This method is being called after the isAuthenticated() method returned a false value, meaning, the user is not logged in.
     It starts the login process.
     The completion should always be called when the process is done - no matter what is the result.
     */
    public func login(_ additionalParameters: [String : Any]?, completion: @escaping ((ZPLoginOperationStatus) -> Void)) {
        if let loginManager = loginManager {
            loginManager.logIn(readPermissions:[ .publicProfile ], viewController: nil) { loginResult in
                switch loginResult {
                case .failed(let error):
                    print(error)
                    completion(.failed)
                case .cancelled:
                    print("User cancelled login.")
                    completion(.cancelled)
                case .success( _, _, let accessToken):
                    print("Logged in!")
                    if accessToken.authenticationToken.isEmptyOrWhitespace() == false {
                        self.setUserToken(token: accessToken.authenticationToken)
                        self.setUserExpirationDate(token: accessToken.expirationDate)
                        completion(.completedSuccessfully)
                    } else {
                        completion(.failed)
                    }
                }
            }
        } else {
            completion(.failed)
        }
    }
    
    /**
     This method is being called in order to start logout process.
     The completion should always be called when the process is done - no matter what is the result.
    */
    public func logout(_ completion: @escaping ((ZPLoginOperationStatus) -> Void)) {
        if let loginManager = loginManager {
            loginManager.logOut()
            completion(.completedSuccessfully)
        } else {
            completion(.failed)
        }
    }
    
    /**
     This methood is called in order to verify if we need to start a login flow
     for example play method is invoked on a player, the player first checks if a login plugin exist
     and if so it creates an instance of this plugin, and invokes this method to check if the user is already logged in
     Returns bool value indicating if the user is already verified if not we start the login proccess
    */
    public func isAuthenticated() -> Bool {
        return self.isTokenValid()
    }
    
    /**
     This methood is called in order to verify if authorization flow is in process
    */
    public func isPerformingAuthorizationFlow() -> Bool {
        return self.isPerformingAuthorizationFlow()
    }

//MARK: Public
    public func getUserExpirationDate() -> Date? {
        return UserDefaults.standard.object(forKey: "fb_expiration_date") as? Date
    }
    
    public func setUserExpirationDate(token: Date?) {
        UserDefaults.standard.set(token, forKey:"fb_expiration_date")
    }
    
    func deleteCookies() {
        let storage = HTTPCookieStorage.shared
        for cookie in storage.cookies! {
            storage.deleteCookie(cookie)
        }
    }
    
//MARK: Private
    private func isTokenValid() -> Bool {
        var retVal = false
        if let expirationDate = self.getUserExpirationDate(),
            self.getUserToken().isEmpty == false {
            let currentDate = Date()
            if expirationDate > currentDate || expirationDate == currentDate {
                retVal = true
            }
        }
        return retVal
    }
}
