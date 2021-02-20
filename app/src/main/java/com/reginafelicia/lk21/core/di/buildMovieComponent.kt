package com.reginafelicia.lk21.core.di


fun getApplicationComponent() = DIApplicationManager.applicationComponent

object DIApplicationManager {
    lateinit var applicationComponent: ApplicationComponent

    fun setupApplicationComponent(applicationComponent: ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }
}

