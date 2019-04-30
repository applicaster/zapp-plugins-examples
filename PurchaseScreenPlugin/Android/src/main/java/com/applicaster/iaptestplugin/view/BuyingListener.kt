package com.applicaster.iaptestplugin.view

/**
 * Set this listener to [PurchaseItemsAdapter] constructor to have a possibility to handle
 * 'BUY' button click in adapter
 */
interface BuyingListener {

    /**
     * @param itemId ([com.android.billingclient.api.SkuDetails.getSku])
     */
    fun onBuyBtnClick(itemId: String)
}