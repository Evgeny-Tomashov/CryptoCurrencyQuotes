package com.devtomashov.ccq.ui.rv_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devtomashov.ccq.R
import com.devtomashov.ccq.data.entity.Quote
import com.devtomashov.ccq.ui.rv_viewholders.QuoteViewHolder

//В параметр передаем слушатель, чтобы мы потом могли обрабатывать нажатия из класса Activity
class QuoteRecyclerAdapter(private val clickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //Здесь у нас хранится список элементов для RV
    private val items = mutableListOf<Quote>()

    //Этот метод нужно переопределить на возврат количества элементов в списке RV
    override fun getItemCount() = items.size

    //В этом методе мы привязываем наш ViewHolder и передаем туда "надутую" верстку котировки
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return QuoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false))
    }

    //В этом методе будет привязка полей из объекта Quote к View из quote.xml
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //Проверяем, какой у нас ViewHolder
        when (holder) {
            is QuoteViewHolder -> {
                //Вызываем метод bind(), который мы создали, и передаем туда объект
                //из нашей базы данных с указанием позиции
                holder.bind(items[position])
            }
        }
    }

    //Метод для добавления объектов в наш список
    fun addItems(list: List<Quote>) {
        //Сначала очищаем(если не реализовать DiffUtils)
        items.clear()
        //Добавляем
        items.addAll(list)
        //Уведомляем RV, что пришел новый список, и ему нужно заново все "привязывать"
        notifyDataSetChanged()
    }


    //Интерфейс для обработки кликов
    interface OnItemClickListener {
        fun click(quote: Quote)
    }
}