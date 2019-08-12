# ZappPluginPlayerExample-iOS

The ZappPluginPlayerExample-iOS is an example project for creating a player plugin for the Applicaster Zapp Platform. You can use this example project as a reference for how to build your own video player plugin.

If you are not familiar with Zapp please visit [our website](http://applicaster.com/?page=product) for more details.

The full [Zapp](http://zapp.applicaster.com) plugins documentation is available [here](http://zapp-tech-book.herokuapp.com).

## Getting Started
Clone this project `$ git clone https://github.com/applicaster/ZappPluginPlayerExample-iOS.git`.

Run `$ pod install` in order to set the workspace.

Open `ZappPluginPlayerExample-iOS.xcworkspace` with Xcode 10.2.1.

## Zapp Plugin Player API
The Zapp plugin player API enables developers to integrate different players to the the Zapp Platform.

The API contains the `ZPPlayerProtocol` which can be easily implemented by subclassing the `APPlugablePlayerBase`.

In order to access the `ZPPlayerProtocol` and the `APPlugablePlayerBase`, you will need to import `ApplicasterSDK` and the `ZappPlugins` frameworks, for example:
``` swift
import Foundation
import ZappPlugins
import ApplicasterSDK
import AVKit
```

### ZPPlayerProtocol
``` swift
    /// Initialization of player instance view controller with item to play.
    ///
    /// - Parameter item: The instance ZPPlayable item.
    /// - Returns: Creates an instance of the player view controller and, in this example, returns a ZappPlayerAdapter instance.
    public static func pluggablePlayerInit(playableItems items: [ZPPlayable]?, configurationJSON: NSDictionary?) -> ZPPlayerProtocol? {
        // Replace this lines with your player implementation
        let instance = ZappPlayerAdapter()
        instance.playerViewController = AVPlayerViewController()
        instance.currentPlayableItem = items?.first
        return instance
    }
    
    /// Returns the view controller of current playable player instance.
    ///
    /// - Returns: Should return a playable view controller (for the specific instance).
    public override func pluggablePlayerViewController() -> UIViewController? {
        // Replace this lines with your player implementation
        if let videoPath = self.currentPlayableItem?.contentVideoURLPath(),
            let videoURL = URL(string: videoPath) {
            
            let player = AVPlayer(url: videoURL)
            self.playerViewController?.player = player
        }
        return self.playerViewController
    }
    
    /// Returns the playing asset.
    ///
    /// - Returns: Should return the playable stream url as NSURL.
    public func pluggablePlayerCurrentUrl() -> NSURL? {
        if let videoPath = currentPlayableItem?.contentVideoURLPath() {
            return NSURL(string: videoPath)
        }
        return nil
    }
    
    /// Returns the current playable item
    ///
    /// - Returns: Should return the current playable item of ZPPlayable type.
    public func pluggablePlayerCurrentPlayableItem() -> ZPPlayable? {
        return currentPlayableItem
    }
    
        /// Start playing with configuration
    ///
    /// - Parameter configuration: ZPPlayerConfiguration object, including few configurations for the player instance. For example, should the player start muted until tapped for the first time.
    public override func pluggablePlayerPlay(_ configuration: ZPPlayerConfiguration?) {
        // Replace this lines with your player implementation
        if self.currentPlayableItem?.isLive() == true {
            self.playVideo()
        }
        else {
            self.playVideo()
        }
    }
    
    /// Pauses active player
    public override func pluggablePlayerPause() {
        // Replace this lines with your player implementation
        if let player = self.playerViewController?.player {
            player.pause()
        }
    }
    
    /// Stop playing loaded item
    public override func pluggablePlayerStop() {
        // Replace this lines with your player implementation
        if let player = self.playerViewController?.player {
            player.pause()
        }
    }
    
    /// Is player playing a video
    ///
    /// - Returns: Returns true if playing a video, otherwise false.
    public override func pluggablePlayerIsPlaying() -> Bool {
        return false
    }
```

#### Available only in Full screen mode
``` swift
    /// Call this method to start playing the given playable. Because this is a full screen player after calling this method the app doesn't have control of it's flow.
    ///
    /// - Parameters:
    ///   - rootViewController: The app root view controller and it's topmostModal, in order to enable to present the player view controller.
    ///   - configuration: ZPPlayerConfiguration object, including few configurations for the player instance. For example, should the player start muted until tapped for the first time.
    public override func presentPlayerFullScreen(_ rootViewController: UIViewController, configuration: ZPPlayerConfiguration?) {
        // Replace this lines with your player implementation
        let animated : Bool = configuration?.animated ?? true;
        
        weak var weakSelf = self;
        let rootVC : UIViewController = rootViewController.topmostModal()
        //Present player
        if let playerVC = self.pluggablePlayerViewController() {
            rootVC.present(playerVC, animated:animated, completion: {
                weakSelf?.playVideo()
            })
        }
    }
```

#### Available only in Inline mode
``` swift
     /// This func is called when a cell is requesting an inline player view to present inside.
    ///
    /// - Parameters:
    ///   - rootViewController: The cell view controller.
    ///   - container: The container view inside the cell.
    public override func pluggablePlayerAddInline(_ rootViewController: UIViewController, container : UIView) {
        // adding the player view to the cell container, replace this lines with your player implementation
        // in the Zapp app the inline player cell is adding the video view in a ContainerView, so you should add it as ChildViewController and not as subview.
        if let playerVC = self.pluggablePlayerViewController() {
            rootViewController.addChildViewController(playerVC, to: container)
            playerVC.view.matchParent()
        }
    }
    
    /// This func is called when a cell is requesting to remove an inline player view that is already presented.
    public override func pluggablePlayerRemoveInline(){
        //get the container
        let container = self.pluggablePlayerViewController()?.view.superview
        super.pluggablePlayerRemoveInline()
        //remove temp view
        container?.removeFromSuperview()
    }

    
```

#### Using the Zapp Plugin Configuration JSON
When creating a plugin in Zapp we can create custom configuration fields. This enable the Zapp user to fill relevant details for the specific plugin. More details can found in the [Zapp Plugin Manifest Format](http://zapp-tech-book.herokuapp.com/zappifest/plugins-manifest-format.html).
You can use that on the plugin level like that:
``` swift
    guard let customParam = configurationJSON?["customParam"] as? String else {
        APLoggerError("Failed to create customParam from the plugin configuration JSON.")
        return nil
    }
```

## Debug
In order to be able to test and debug your project A->Z, you will need add your plugin to the `zapp-ios-plugins-starter-kit`, which can be cloned [here](https://github.com/applicaster/zapp-ios-plugins-starter-kit).

## TO-DOs
- [ ] readme improvements:
    - add details about the `zapp-ios-plugins-starter-kit`
    - add link to the iOS plugin player full documentation.
