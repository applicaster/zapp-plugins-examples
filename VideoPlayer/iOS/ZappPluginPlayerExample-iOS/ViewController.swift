//
//  ViewController.swift
//  ZappPluginPlayerExample-iOS
//
//  Created by Udi Lumnitz on 19/06/2017.
//  Copyright © 2017 Applicaster. All rights reserved.
//

import UIKit
import ZappPlugins
import ApplicasterSDK

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
        
    }

    
    @IBAction func buttonPlay_clicked() {
        self.presentPlayer()
    }
    
    func presentPlayer() {
        let item:ZPPlayable = APURLPlayable(streamURL: "https://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4", name: "Test Video", description: "")
        let pluggablePlayer = ZPPlayerManager.sharedInstance.create(playableItem: item)
        pluggablePlayer.presentPlayerFullScreen?(self, configuration: nil) {
            pluggablePlayer.pluggablePlayerPlay(nil)
        }
    }
}

