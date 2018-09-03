# ZappLoginPluginExample-iOS

The ZappLoginPluginExample is an example project for creating a login plugin for the Applicaster Zapp Platform. You can use this example project as a reference for how to build your own login plugin.

If you are not familiar with Zapp please visit [our website](http://applicaster.com/?page=product) for more details.

The full [Zapp](http://zapp.applicaster.com) plugins documentation is available [here](https://developer-zapp.applicaster.com).

When you are starting a new iOS plugin our recommendation is to install our [Xcode templates for Applicaster plugins](https://github.com/applicaster/zapp-plugins-ios-templates). The templates will enable you to chose the plugin type in the Xcode "new project" screen. After selecting the plugin type, you will need to provide few general details on the plugin. Then, it will generate a new plugin project that includes the deployment files, like podspec and the plugin_manifest.json, and the plugin class itself including the relevant Zapp protocol.

## Getting Started
Clone this project `$ git clone https://github.com/applicaster/zapp-plugins-examples.git`.
and navigate to LoginPlugin -> iOS
Run `$ pod update` in order to set the workspace.

Open `ZappLoginPluginExample-iOS.xcworkspace` with Xcode 9.4.

## Zapp Login Plugin API
The Zapp login plugin API enables developers to integrate different login providers to the the Zapp Platform.

The API contains the `ZPLoginProviderProtocol` and `ZPLoginProviderUserDataProtocol`
In order to access the `ZPLoginProviderProtocol` and the `ZPLoginProviderUserDataProtocol`, you will need to import `ApplicasterSDK` and the `ZappLoginPluginsSDK` frameworks, for example:
``` swift
import Foundation
import ZappLoginPluginsSDK
import ApplicasterSDK
```

### ZPLoginProviderProtocol
``` swift
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
     This method is being called after the isAuthenticated() method returned a false value, meaning, the user is not 		 logged in, It starts the login process.
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
     Returns bool value indicating if the user is already verified if not  we start the login proccess
     in this example we check if the login token exists and if it is valid
    */
    public func isAuthenticated() -> Bool {
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
    
	/**
     This methood is called in order to verify if authorization flow is in process
    */
    public func isPerformingAuthorizationFlow() -> Bool {
        return self.isPerformingAuthorizationFlow()
    }
```

### ZPLoginProviderUserDataProtocol
``` swift  
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
    
    /**
     This methood is called in order to verify if we need to start a login flow with respect to the policies dictionary
     @params policies - dictionary containing policies to be considered when returning the result 
     Returns bool value indicating if the user is already verified if not  we start the login proccess
     in this example we check if the login token exists and if it is valid
    */
    public func isUserComply(policies:[String: NSObject]) -> Bool {
        return self.isAuthenticated()
    }
```

#### Using the Zapp Plugin Configuration JSON
When creating a plugin in Zapp we can create custom configuration fields. This enable the Zapp user to fill relevant details for the specific plugin. More details can found in the [Zapp Plugin Manifest Format](http://developer-zapp.applicaster.com/zappifest/plugins-manifest-format.html).
You can use that on the plugin level like that:
``` swift
    guard let customParam = configurationJSON?["customParam"] as? String else {
        APLoggerError("Failed to create customParam from the plugin configuration JSON.")
        return nil
    }
```
