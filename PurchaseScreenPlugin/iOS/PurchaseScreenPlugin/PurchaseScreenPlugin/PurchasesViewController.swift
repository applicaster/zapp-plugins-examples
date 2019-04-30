//
//  PurchasesViewController.swift
//  IAPTesterPlugin
//
//  Created by Roman Karpievich on 4/17/19.
//  Copyright © 2019 Roman Karpievich. All rights reserved.
//

import UIKit
import StoreKit
import ApplicasterIAP

class PurchasesViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    private var inAppList: [SKProduct] = []
    private var invalidIdentifiers: [String] = []
    private let purchaseIdentifiers: Set<String> = ["com.yourappid.item1",
                                                    "com.yourappid.item2",
                                                    "com.yourappid.item3",
                                                    "com.yourappid.item4"]
    private var purchasedItems: Set<String> = []
    
    @IBOutlet var inappsTableView: UITableView!

    override func viewDidLoad() {
        super.viewDidLoad()
        
        BillingHelper.sharedInstance.restore { (result) in
            switch result {
            case .success(let paymentTransactions):
                self.purchasedItems = Set(paymentTransactions.map({ $0.payment.productIdentifier }))
                self.refreshProductList()
            case .failure(let error):
                print(error.localizedDescription)
            }
        }
    }
    
    // MARK: - UITableViewDataSource methods
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return inAppList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "PurchaseCell",
                                                       for: indexPath) as? PurchaseTableViewCell,
            indexPath.row < inAppList.count else {
            return UITableViewCell()
        }
        
        let product = inAppList[indexPath.row]
        
        cell.textLabel?.text = product.localizedTitle
        cell.buyAction = {
            BillingHelper.sharedInstance.purchase(product, completion: { (result) in
                switch result {
                case .success(let purchase):
                    let productIdentifier = purchase.item.productIdentifier
                    self.purchasedItems.insert(productIdentifier)
                    tableView.reloadRows(at: [indexPath], with: .none)
                case .failure(let error):
                    print(error.localizedDescription)
                }
            })
        }
        
        if purchasedItems.contains(product.productIdentifier) {
            cell.purchaseState = .bought
        } else {
            cell.purchaseState = .available
        }
        
        return cell
    }
    
    // MARK: - Private methods
    
    private func refreshProductList() {
        BillingHelper.sharedInstance.products(purchaseIdentifiers) { (result) in
            switch result {
            case .success(let response):
                self.inAppList = response.products
                self.invalidIdentifiers = response.invalidIDs
                self.inappsTableView.reloadData()
            case .failure(let error):
                print(error.localizedDescription)
                return
            }
        }
    }
}

extension UIViewController {
    class func storyboardInstance() -> Self {
        return genericStoryboardInstance()
    }
    
    private class func genericStoryboardInstance<T>() -> T {
        let bundle = Bundle(for: T.self as! AnyClass)
        let storyboard = UIStoryboard(name: String(describing: self),
                                      bundle: bundle)
        return storyboard.instantiateInitialViewController() as! T
    }
}
