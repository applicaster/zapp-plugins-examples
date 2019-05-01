package com.applicaster.iaptestplugin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.android.billingclient.api.BillingClient
import com.applicaster.iaptestplugin.view.IAPTestActivity
import com.applicaster.plugin_manager.screen.PluginScreen
import java.io.Serializable
import java.util.HashMap

class IAPPlugin : PluginScreen {

    override fun generateFragment(screenMap: HashMap<String, Any>?, dataSource: Serializable?): Fragment? = null

    override fun present(
        context: Context?,
        screenMap: HashMap<String, Any>?,
        dataSource: Serializable?,
        isActivity: Boolean
    ) {
        val intent = Intent(context, IAPTestActivity::class.java)
        val bundle = Bundle()
        bundle.apply { putSerializable(KEY_PURCHASES, getPurchaseItemsIds()) }
        intent.putExtras(bundle)
        context?.startActivity(intent)
    }

    fun initTest(context: Context?) {
        val intent = Intent(context, IAPTestActivity::class.java)
        val bundle = Bundle()
        bundle.apply { putSerializable(KEY_PURCHASES, getPurchaseItemsIds()) }
        intent.putExtras(bundle)
        context?.startActivity(intent)
    }

    private fun getPurchaseItemsIds(): ItemsInfo {
        val itemsInfo = ItemsInfo()
        itemsInfo.addItemInfo("product_id", BillingClient.SkuType.INAPP)
        return itemsInfo
    }

    companion object {
        const val KEY_PURCHASES = "Purchases"
    }
}