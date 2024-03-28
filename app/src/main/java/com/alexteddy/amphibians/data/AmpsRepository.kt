package com.alexteddy.amphibians.data

import com.alexteddy.amphibians.network.Amphibian
import com.alexteddy.amphibians.network.AmpsApiService

interface AmpsRepository {
    suspend fun getAmphibians(): List<Amphibian>
}

class NetworkAmpsRepository(
    private val ampsApiService: AmpsApiService
): AmpsRepository {
    override suspend fun getAmphibians(): List<Amphibian> =  ampsApiService.getAmphibiansService()
}