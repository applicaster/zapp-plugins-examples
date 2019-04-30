//
//  PurchaseTableViewCell.swift
//  IAPTesterPlugin
//
//  Created by Roman Karpievich on 4/18/19.
//  Copyright © 2019 Roman Karpievich. All rights reserved.
//

import UIKit

enum PurchaseState: String {
    case available = "Buy"
    case bought = "Bought"
}

class PurchaseTableViewCell: UITableViewCell {

    @IBOutlet var buyButton: UIButton!
    
    public var purchaseState: PurchaseState = .available {
        didSet {
            switch purchaseState {
            case .available:
                buyButton.addTarget(self, action: #selector(buyButtonPressed), for: .touchDown)
                buyButton.setTitle("Buy", for: .normal)
                buyButton.isEnabled = true
            case .bought:
                buyButton.isEnabled = false
                buyButton.setTitle("Bought", for: .normal)
            }
        }
    }
    
    public var buyAction: () -> Void = {}
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    @objc private func buyButtonPressed() {
        buyAction()
    }

}
