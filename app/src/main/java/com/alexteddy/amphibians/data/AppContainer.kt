package com.alexteddy.amphibians.data

import com.alexteddy.amphibians.network.AmpsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val ampsRepository: AmpsRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl =
        "https://android-kotlin-fun-mars-server.appspot.com"

    private val retrofit = Retrofit.Builder() // Dung bo tao doi tuong Retrofit
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl) // ket noi den web service nao
        .build() // Tao doi tuong Retrofit

    private val retrofitService : AmpsApiService by lazy {
        retrofit.create(AmpsApiService::class.java)
    }

    override val ampsRepository: AmpsRepository by lazy {
        NetworkAmpsRepository(retrofitService)
    }
}