package com.applicaster.advertisement

import android.content.res.Resources
import android.util.DisplayMetrics
import com.applicaster.plugins.advertisement.view.AdType
import com.applicaster.plugins.advertisement.view.Size
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdSize.*


object AdSizeMapper {

    fun map(adType:AdType, adviewSize: String): AdSize {
        return when(adType) {
            AdType.INTERSTITIAL -> FULL_BANNER
            AdType.SCREEN_BANNER -> BANNER
            AdType.INLINE_BANNER ->
                when (adviewSize) {
                    "STANDARD_BANNER" -> BANNER
                    "SMART_BANNER" -> SMART_BANNER
                    "BOX_BANNER" -> AdSize(300, 250)
                    else -> BANNER

                }
        }
    }
}

object SizeMapper {
    var SMART_BANNER = 50
    fun map(adType: AdType, adviewSize: String): Size {
        val width = Resources.getSystem().displayMetrics.widthPixels.px2dp()
        val height = Resources.getSystem().displayMetrics.heightPixels.px2dp()
        return when (adviewSize) {
            "STANDARD_BANNER" -> Size(AdSize.BANNER.width, AdSize.BANNER.height)
            "SMART_BANNER" -> Size(width, SMART_BANNER)
            "BOX_BANNER" -> Size(300, 250)
            else -> when(adType) {
                AdType.INTERSTITIAL -> Size(width, height)
                AdType.SCREEN_BANNER -> Size(AdSize.BANNER.width, AdSize.BANNER.height)
                else -> Size(AdSize.BANNER.width, AdSize.BANNER.height)
                }
        }
    }
}

fun Int.px2dp() : Int {
    val metrics = Resources.getSystem().displayMetrics
    return (this / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
}