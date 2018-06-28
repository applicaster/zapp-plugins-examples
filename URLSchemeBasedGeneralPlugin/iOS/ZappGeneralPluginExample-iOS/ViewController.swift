//
//  ViewController.swift
//  ZappGeneralPluginExample-iOS
//
//  Created by Liviu Romascanu on 28/06/2018.
//  Copyright © 2018 Applicaster. All rights reserved.
//

import UIKit
import SampleGeneralPlugin

class ViewController: UIViewController {
    @IBOutlet weak var configurationTextField: UITextField!
    @IBOutlet weak var parametersTextField: UITextField!
    var sampleGeneralPlugin: SampleGeneralPlugin?
    
    @IBAction func initializePluginClicked(_ sender: Any) {
        if let data = configurationTextField.text?.data(using: String.Encoding.utf8),
            let configuration = try? JSONSerialization.jsonObject(with: data, options: JSONSerialization.ReadingOptions.mutableContainers) as? NSDictionary {
            sampleGeneralPlugin = SampleGeneralPlugin(configurationJSON: configuration)
        } else {
            let alert = UIAlertController(title: nil, message: "Please enter a valid json in the configuration field", preferredStyle: .alert)
            alert.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
            self.present(alert, animated: true, completion: nil)
        }
    }
    
    @IBAction func handleURLSchemeClicked(_ sender: Any) {
        if let sampleGeneralPlugin = sampleGeneralPlugin {
            if let data = parametersTextField.text?.data(using: String.Encoding.utf8),
                let params = try? JSONSerialization.jsonObject(with: data, options: JSONSerialization.ReadingOptions.mutableContainers) as? NSDictionary,
                let dictionaryParams = params {
                sampleGeneralPlugin.handleUrlScheme(dictionaryParams)
            } else {
                let alert = UIAlertController(title: nil, message: "Please enter a valid json in the configuration field", preferredStyle: .alert)
                alert.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
                self.present(alert, animated: true, completion: nil)
            }
        } else {
            let alert = UIAlertController(title: nil, message: "Please first initialize a plugin", preferredStyle: .alert)
            alert.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
            self.present(alert, animated: true, completion: nil)
        }
    }
}

