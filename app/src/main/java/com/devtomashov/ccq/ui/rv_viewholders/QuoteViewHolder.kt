package com.devtomashov.ccq.ui.rv_viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.devtomashov.ccq.data.Quote
import com.devtomashov.ccq.databinding.QuoteBinding

class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val quoteBinding = QuoteBinding.bind(itemView)

    //Привязываем View из layout quote.xml к переменным
    fun bind(quote: Quote) {

        //Устанавливаем позицию
        quoteBinding.positionCC.text = quote.positionCC.toString()
        //Устанавливаем наименование
        quoteBinding.nameCC.text = quote.nameCC
        //Устанавливаем цену
        quoteBinding.priceCC.text = quote.priceCC.toString()
        //Устанавливаем изменение цены за сутки
        quoteBinding.changePriceCC24.text = quote.changePriceCC24.toString()
        //Устанавливаем капитализацию
        quoteBinding.marketCapCC.text = quote.marketCapCC.toString()
    }
}