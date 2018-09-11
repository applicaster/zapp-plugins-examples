package com.applicaster.advertisement

import android.annotation.SuppressLint
import android.content.Context
import com.applicaster.plugins.advertisement.model.AdConfig
import com.applicaster.plugins.advertisement.presenter.AdViewPresenter
import com.applicaster.plugins.advertisement.view.AdType
import com.applicaster.plugins.advertisement.view.AdView
import com.applicaster.plugins.advertisement.view.AdViewState
import com.applicaster.plugins.advertisement.view.Size
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.android.gms.ads.doubleclick.PublisherAdView
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd

class Presenter(var context: Context) : AdViewPresenter {

    lateinit var publisherAdView: PublisherAdView
    lateinit var publisherInterstitialAd: PublisherInterstitialAd
    lateinit var adView: AdView
    lateinit var adConfig: AdConfig

    override fun init(component: AdView) {
        adView = component
    }

    override fun loadAd(adConfig: AdConfig) {
        this.adConfig = adConfig

        if (adConfig.adType == AdType.INTERSTITIAL) {
            publisherInterstitialAd = PublisherInterstitialAd(context)
            publisherInterstitialAd.adListener = object : AdListener() {
                override fun onAdLoaded() {
                    super.onAdLoaded()
                    adView.adLoaded(this@Presenter, null)
                    adView.stateChanged(this@Presenter, AdViewState.Loaded)
                }

                override fun onAdFailedToLoad(errorCode: Int) {
                    super.onAdFailedToLoad(errorCode)
                    adView.adLoadFailed(this@Presenter, Exception(javaClass.simpleName
                            + " could not load AdView. Failed with error code " + errorCode))
                    adView.stateChanged(this@Presenter, AdViewState.Failed)
                }

                override fun onAdOpened() {
                    super.onAdOpened()
                    adView.stateChanged(this@Presenter, AdViewState.Impressed)
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    adView.stateChanged(this@Presenter, AdViewState.Impressed)
                }

                override fun onAdClosed() {
                    super.onAdClosed()
                    adView.stateChanged(this@Presenter, AdViewState.Closed)
                }
            }
            publisherInterstitialAd.adUnitId = adConfig.adUnitId
            loadInterstitialAd()
        } else {
            publisherAdView = PublisherAdView(context)

            publisherAdView.adListener = object : AdListener() {
                override fun onAdLoaded() {
                    super.onAdLoaded()
                    adView.adLoaded(this@Presenter, publisherAdView)
                    adView.stateChanged(this@Presenter, AdViewState.Loaded)
                }

                override fun onAdFailedToLoad(errorCode: Int) {
                    super.onAdFailedToLoad(errorCode)
                    adView.adLoadFailed(this@Presenter, Exception(javaClass.simpleName
                            + " could not load AdView. Failed with error code " + errorCode))
                    adView.stateChanged(this@Presenter, AdViewState.Failed)
                }

                override fun onAdOpened() {
                    super.onAdOpened()
                    adView.stateChanged(this@Presenter, AdViewState.Impressed)
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    adView.stateChanged(this@Presenter, AdViewState.Impressed)
                }

                override fun onAdClosed() {
                    super.onAdClosed()
                    adView.stateChanged(this@Presenter, AdViewState.Closed)
                }
            }
            //var v = View(context)
            publisherAdView.adUnitId = adConfig.adUnitId
            publisherAdView.setAdSizes(AdSizeMapper.map(adConfig.adType,adConfig.adSize))

            loadBannerAd()
        }
    }
    @SuppressLint("MissingPermission")
    fun loadBannerAd() {
        val adRequest = PublisherAdRequest.Builder().build()
        adView.stateChanged(this@Presenter, AdViewState.Loading)
        publisherAdView.loadAd(adRequest)
    }

    @SuppressLint("MissingPermission")
    fun loadInterstitialAd() {
        adView.stateChanged(this@Presenter, AdViewState.Loading)
        publisherInterstitialAd.loadAd(PublisherAdRequest.Builder().build())
    }


    override fun getSize(withContainer: Boolean): Size = SizeMapper.map(adConfig.adType, adConfig.adSize)

    override fun getProviderName(): String = "DFP"

    override fun reloadAdWithSize(adSize: String) {
    }

    override fun showInterstitial() {
        publisherInterstitialAd.show()
    }

    override fun getConfig(): AdConfig = this.adConfig


}

