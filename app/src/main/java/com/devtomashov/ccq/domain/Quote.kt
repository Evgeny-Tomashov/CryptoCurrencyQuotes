package com.devtomashov.ccq.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize

data class Quote(
    val positionCC: Int,
    val nameCC: String,
    val priceCC: Double,
    val changePriceCC24: Double,
    val marketCapCC: Double,
    var isInFavorites: Boolean = false
) : Parcelable
