package com.devtomashov.ccq.data

data class Data(
    val changePercent24Hr: Double,
    val id: String,
    val marketCapUsd: Double,
    val maxSupply: String,
    val name: String,
    val priceUsd: Double,
    val rank: Int,
    val supply: String,
    val symbol: String,
    val volumeUsd24Hr: String,
    val vwap24Hr: String
)
