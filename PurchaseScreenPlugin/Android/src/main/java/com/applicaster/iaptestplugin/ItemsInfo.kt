package com.applicaster.iaptestplugin

import java.io.Serializable

/**
 *  Class that holds map with items id's as key and SkuType
 *  as value([com.android.billingclient.api.BillingClient.SkuType])
 */
class ItemsInfo : Serializable {
    private val purchaseItemsMap: HashMap<String, String> = HashMap()


    fun addItemInfo(purchaseId: String, purchaseType: String) {
        purchaseItemsMap[purchaseId] = purchaseType
    }

    fun getAllItems(): HashMap<String, String> = purchaseItemsMap
}
