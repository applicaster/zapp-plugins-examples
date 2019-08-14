//
//  ZappPlayerAdapter.swift
//  ZappPluginPlayerExample-iOS
//
//  Created by Udi Lumnitz on 19/06/2017.
//  Copyright © 2017 Applicaster. All rights reserved.
//

import Foundation
import ZappPlugins
import ApplicasterSDK
import AVKit

public class ZappPlayerAdapter: APPlugablePlayerBase {
    
    // MARK: - Properties
    
    public var playerViewController: AVPlayerViewController?
    var currentPlayableItem: ZPPlayable?
    
    // MARK: - ZPPlayerProtocol
    
    /// Initialization of player instance view controller with item to play.
    ///
    /// - Parameter 
    ///   - item: The instance ZPPlayable item.
    ///   - configurationJSON: A NSDictionary of the plugin custom configuraiton fields.
    /// - Returns: Creates an instance of the player view controller and, in this example, returns a ZappPlayerAdapter instance.
    public static func pluggablePlayerInit(playableItems items: [ZPPlayable]?, configurationJSON: NSDictionary?) -> ZPPlayerProtocol? {
        // Replace this lines with your player implementation
        
        // configurationJSON example (not being used in the AVPlayer example):
//      guard let customParam = configurationJSON?["customParam"] as? String else {
//          APLoggerError("Failed to create customParam from the plugin configuration JSON.")
//          return nil
//      }
        
        // creating the AVPlayer instance
        let instance = ZappPlayerAdapter()
        instance.playerViewController = AVPlayerViewController()
        instance.currentPlayableItem = items?.first
        
        // adding observer for item did play to end time in order to be able to dissmiss the player in such a case
        NotificationCenter.default.addObserver(instance, selector: #selector(ZappPlayerAdapter.playerDidFinishPlaying), name: Notification.Name.AVPlayerItemDidPlayToEndTime, object: nil)
        
        // returning our player instance
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

    // MARK: - Available only in Full screen mode
    
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
    
    // MARK: - Available only in Inline mode
    
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
    
    // MARK: - Private
    
    public func playVideo() {
        // Replace this lines with your player implementation
        if let player = self.playerViewController?.player {
            player.play()
        }
    }
  
    open override func pluggablePlayerType() -> ZPPlayerType {
        return ZappPlayerAdapter.pluggablePlayerType()
    }
    
    public static func pluggablePlayerType() -> ZPPlayerType {
        return .undefined
    }
    
    // handling the did play to end time notification - dissmising the player view controller and removing the observer
    @objc public func playerDidFinishPlaying(notification:Notification) {
        self.pluggablePlayerViewController()?.dismiss(animated: true, completion: nil)
        NotificationCenter.default.removeObserver(self, name: Notification.Name.AVPlayerItemDidPlayToEndTime, object: nil)
    }
}
