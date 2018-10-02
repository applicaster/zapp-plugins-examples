package com.applicaster.advertisement

import android.content.Context
import com.applicaster.plugin_manager.Plugin
import com.applicaster.plugins.advertisement.AdPlugin
import com.applicaster.plugins.advertisement.presenter.AdViewPresenter
import com.applicaster.plugins.advertisement.view.AdView

class Plugin : AdPlugin {

    override fun createAd(context: Context, component: AdView): AdViewPresenter {
        var ret = Presenter(context)
        ret.init(component)
        return ret
    }

    override fun setPluginModel(plugin: Plugin?) {}
}