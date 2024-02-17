package com.devtomashov.ccq.data

import com.devtomashov.ccq.domain.Quote

object Converter {
    fun convertApiListToDtoList(list: List<Data>?): List<Quote> {
        val result = mutableListOf<Quote>()
        list?.forEach {
            result.add(Quote(
                positionCC = it.rank,
                nameCC = it.name,
                priceCC = it.priceUsd,
                changePriceCC24 = it.changePercent24Hr,
                marketCapCC = it.marketCapUsd,
                isInFavorites = false
            ))
        }

        return result
    }
}