package com.alexteddy.amphibians.network

import retrofit2.http.GET

interface AmpsApiService {
    @GET("amphibians")
    suspend fun getAmphibiansService(): List<Amphibian>
}