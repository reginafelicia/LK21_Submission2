package com.reginafelicia.lk21

import android.app.Application
import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat
import com.reginafelicia.core.BaseApplication
import com.reginafelicia.lk21.core.di.ApplicationComponent
import com.reginafelicia.lk21.core.di.DIApplicationManager
import com.reginafelicia.lk21.core.di.DaggerApplicationComponent
import com.reginafelicia.lk21.core.di.module.ApplicationModule


class LK21App : BaseApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        DIApplicationManager.setupApplicationComponent(buildApplicationComponent(this))
        SplitCompat.install(this)
    }

    private fun buildApplicationComponent(application: Application): ApplicationComponent {
        return DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(application)).build()
    }

}