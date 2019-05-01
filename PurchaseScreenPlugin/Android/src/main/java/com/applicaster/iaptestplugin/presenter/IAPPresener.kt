package com.applicaster.iaptestplugin.presenter

import com.applicaster.iaptestplugin.ItemsInfo

interface IAPPresener {
    fun onCreate(purchasesInfo: ItemsInfo)
    fun onDestroy()
    fun onBuyClicked(skuId: String)
}