package com.reginafelicia.core

import android.app.Application

const val BASE_URL = "https://api.themoviedb.org/3/"

open class BaseApplication : Application() {
    open fun getBaseUrl() = BASE_URL
}