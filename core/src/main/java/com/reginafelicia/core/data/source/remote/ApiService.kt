package com.reginafelicia.core.data.source.remote

interface ApiService {
    fun <T> create(type: Class<T>): T
}

inline fun <reified T> ApiService.create(): T {
    return create(T::class.java)
}