package com.devtomashov.ccq.domain

data class Quote(
    val positionCC: Int,
    val nameCC: String,
    val priceCC: Double,
    val changePriceCC24: Double,
    val marketCapCC: Long
)
