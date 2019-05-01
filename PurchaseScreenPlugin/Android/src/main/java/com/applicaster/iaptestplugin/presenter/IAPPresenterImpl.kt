package com.applicaster.iaptestplugin.presenter

import android.app.Activity
import android.support.v4.app.FragmentActivity
import android.widget.Toast
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.SkuDetails
import com.applicaster.iap.BillingListener
import com.applicaster.iap.GoogleBillingHelper
import com.applicaster.iaptestplugin.ItemsInfo
import com.applicaster.iaptestplugin.view.ErrorDialog
import com.applicaster.iaptestplugin.view.IAPTestActivityView
import com.applicaster.iaptestplugin.view.PurchaseItem

class IAPPresenterImpl(
    private val activity: Activity,
    private val view: IAPTestActivityView
) : IAPPresener,
    BillingListener {

    private val skuDetailsList: ArrayList<SkuDetails> = arrayListOf()
    private val purchasesList: ArrayList<PurchaseItem> = arrayListOf()
    private var errorDialog: ErrorDialog? = null

    override fun onCreate(purchasesInfo: ItemsInfo) {
        view.initIAPTestActivityView()
        GoogleBillingHelper.init(activity.applicationContext, this)

//        filter items by SKU type
        val inAppPurchasesList = ArrayList<String>()
        purchasesInfo.getAllItems().filter {
            it.value == BillingClient.SkuType.INAPP
        }.map {
            inAppPurchasesList.add(it.key)
        }

        val subsPurchasesList = ArrayList<String>()
        purchasesInfo.getAllItems().filter {
            it.value == BillingClient.SkuType.SUBS
        }.map {
            subsPurchasesList.add(it.key)
        }

//        load Sku details for each SKU type
        if (inAppPurchasesList.isNotEmpty())
            GoogleBillingHelper.loadSkuDetails(BillingClient.SkuType.INAPP, inAppPurchasesList)
        if (subsPurchasesList.isNotEmpty())
            GoogleBillingHelper.loadSkuDetails(BillingClient.SkuType.SUBS, subsPurchasesList)
    }

    override fun onDestroy() {
    }

    override fun onBuyClicked(skuId: String) {
        skuDetailsList.find { it.sku == skuId }?.also { GoogleBillingHelper.purchase(activity, it) }
    }

    //region IAP framework callbacks
    override fun onPurchaseLoaded(purchases: List<Purchase>) {
        Toast.makeText(activity, "Purchase loaded list size: ${purchases.size}", Toast.LENGTH_LONG).show()
        purchases.forEach {
            GoogleBillingHelper.consume(it)
        }
    }

    override fun onPurchaseLoadingFailed(statusCode: Int, description: String) {
        showErrorDialog(description)
    }

    override fun onSkuDetailsLoaded(skuDetails: List<SkuDetails>) {
        Toast.makeText(activity, "Sku details loaded list size: ${skuDetails.size}", Toast.LENGTH_LONG).show()
        skuDetailsList.addAll(skuDetails)

        skuDetails.forEach {
            purchasesList.add(
                PurchaseItem(
                    id = it.sku,
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    skuType = it.type
                )
            )
        }
        view.setPurchaseItems(purchasesList)
    }

    override fun onSkuDetailsLoadingFailed(statusCode: Int, description: String) {
        showErrorDialog(description)
    }

    override fun onPurchaseConsumed(purchaseToken: String) {
        Toast.makeText(activity, "Purchase consumed token: $purchaseToken", Toast.LENGTH_LONG).show()
    }

    override fun onPurchaseConsumptionFailed(statusCode: Int, description: String) {
        showErrorDialog(description)
    }
    //end region

    private fun showErrorDialog(message: String) {
        if (activity is FragmentActivity) {
            if (errorDialog == null) {
                errorDialog = ErrorDialog.newInstance(message)
                errorDialog?.show(activity.supportFragmentManager, "${ErrorDialog::class.java.simpleName}-$message")
            } else {
                errorDialog?.setMessage(message)
                errorDialog?.show(activity.supportFragmentManager, "${ErrorDialog::class.java.simpleName}-$message")
            }
        }
    }
}