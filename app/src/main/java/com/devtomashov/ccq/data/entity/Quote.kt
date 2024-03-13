package com.devtomashov.ccq.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "cached_quotes", indices = [Index(value = ["nameCC"], unique = true)])
data class Quote(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "positionCC") val positionCC: Int,
    @ColumnInfo(name = "nameCC") val nameCC: String,
    @ColumnInfo(name = "priceCC") val priceCC: Double,
    @ColumnInfo(name = "changePriceCC24") val changePriceCC24: Double,
    @ColumnInfo(name = "marketCapCC") val marketCapCC: Double,
    var isInFavorites: Boolean = false
) : Parcelable