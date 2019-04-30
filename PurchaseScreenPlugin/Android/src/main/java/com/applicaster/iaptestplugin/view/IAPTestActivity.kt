package com.applicaster.iaptestplugin.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.applicaster.iaptestplugin.IAPPlugin
import com.applicaster.iaptestplugin.ItemsInfo
import com.applicaster.iaptestplugin.R
import com.applicaster.iaptestplugin.presenter.IAPPresener
import com.applicaster.iaptestplugin.presenter.IAPPresenterImpl
import kotlinx.android.synthetic.main.activity_iaptest.*

class IAPTestActivity : AppCompatActivity(), IAPTestActivityView {

    private lateinit var presener: IAPPresener
    private lateinit var purchasesAdapter: PurchaseItemsAdapter
    private lateinit var buyingListener: BuyingListener
    private lateinit var rvPurchasesList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iaptest)

        val extras: Bundle? = intent.extras
        val purchasesInfo = extras?.get(IAPPlugin.KEY_PURCHASES) as ItemsInfo
        setView()

        presener = IAPPresenterImpl(this, this)
        initListeners()
        presener.onCreate(purchasesInfo)
    }

    private fun initListeners() {
        buyingListener = object : BuyingListener {
            override fun onBuyBtnClick(itemId: String) {
                presener.onBuyClicked(itemId)
            }
        }
    }

    private fun setView() {
        rvPurchasesList = rv_purchase_items
    }

    override fun initIAPTestActivityView() {
        purchasesAdapter = PurchaseItemsAdapter(buyingListener)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvPurchasesList.setHasFixedSize(true)
        rvPurchasesList.layoutManager = layoutManager
        rvPurchasesList.itemAnimator = DefaultItemAnimator()
        rvPurchasesList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rvPurchasesList.adapter = purchasesAdapter
    }

    override fun setPurchaseItems(purchases: List<PurchaseItem>) {
        purchasesAdapter.removeAllItems()
        purchasesAdapter.setPurchaseItems(purchases)
    }
}
