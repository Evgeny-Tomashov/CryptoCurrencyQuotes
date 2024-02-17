package com.devtomashov.ccq.ui.rv_viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.devtomashov.ccq.domain.Quote
import com.devtomashov.ccq.databinding.QuoteItemBinding
import kotlin.math.roundToInt

class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val quoteBinding = QuoteItemBinding.bind(itemView)

    //Привязываем View из layout quote.xml к переменным
    fun bind(quote: Quote) {

        //Округление значений
        var priceCCRounded = quote.priceCC
        priceCCRounded = (priceCCRounded * 1000).roundToInt() / 1000.0

        var changePriceCC24Rounded = quote.changePriceCC24
        changePriceCC24Rounded = (changePriceCC24Rounded * 100).roundToInt() / 100.0

        val marketCapCCRounded = quote.marketCapCC.toLong()

        //Устанавливаем позицию
        quoteBinding.positionCC.text = quote.positionCC.toString()
        //Устанавливаем наименование
        quoteBinding.nameCC.text = quote.nameCC
        //Устанавливаем цену
        quoteBinding.priceCC.text = priceCCRounded.toString()
        //Устанавливаем изменение цены за сутки
        quoteBinding.changePriceCC24.text = changePriceCC24Rounded.toString()
        //Устанавливаем капитализацию
        quoteBinding.marketCapCC.text = marketCapCCRounded.toString()
    }
}