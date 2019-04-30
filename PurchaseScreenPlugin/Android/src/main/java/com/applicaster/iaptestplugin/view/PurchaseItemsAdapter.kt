package com.applicaster.iaptestplugin.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.android.billingclient.api.BillingClient
import com.applicaster.iaptestplugin.R
import kotlinx.android.synthetic.main.layout_purchase_item.view.*

class PurchaseItemsAdapter(private val listener: BuyingListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val purchaseItemsList: ArrayList<PurchaseItem> = arrayListOf()

    inner class PurchaseItemsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var purchaseItemTitle: TextView = itemView.tv_title
        var purchaseItemDescription: TextView = itemView.tv_description
        var purchaseItemPrice: TextView = itemView.tv_price
        var btnBuy: Button = itemView.btn_buy
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_purchase_item, parent, false)
        return PurchaseItemsHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = holder as PurchaseItemsHolder
        val purchaseItem: PurchaseItem = purchaseItemsList[vh.adapterPosition]

        vh.purchaseItemTitle.text = purchaseItem.title
        vh.purchaseItemDescription.text = purchaseItem.description
        vh.purchaseItemPrice.text = purchaseItem.price
        if (purchaseItem.skuType == BillingClient.SkuType.INAPP) vh.btnBuy.text = "Buy" else vh.btnBuy.text = "Subscribe"
        vh.btnBuy.setOnClickListener { listener.onBuyBtnClick(purchaseItemsList[vh.adapterPosition].id) }
    }

    override fun getItemCount(): Int = purchaseItemsList.size

    fun setPurchaseItems(purchaseItems: List<PurchaseItem>) {
        purchaseItemsList.addAll(purchaseItems)
        notifyDataSetChanged()
    }

    fun removeAllItems() {
        purchaseItemsList.clear()
        notifyDataSetChanged()
    }
}