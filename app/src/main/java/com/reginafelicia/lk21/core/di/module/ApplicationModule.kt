package com.reginafelicia.lk21.core.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val appContext: Context) {

    @Provides
    internal fun provideAppContext(): Context = appContext
}