package com.devtomashov.ccq.ui.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.devtomashov.ccq.data.Quote
import com.devtomashov.ccq.databinding.FragmentQuotesBinding
import com.devtomashov.ccq.ui.rv_adapters.QuoteRecyclerAdapter

class QuotesFragment : Fragment() {

    private var _binding: FragmentQuotesBinding? = null
    private lateinit var quotesAdapter: QuoteRecyclerAdapter

    private val quotesDataBase = listOf(
        Quote(1, "Bitcoin", 44128.12, 875.1, 875000000000),
        Quote(2, "Bitcoin", 44128.12, 875.1, 875000000000),
        Quote(3, "Bitcoin", 44128.12, 875.1, 875000000000),
        Quote(4, "Bitcoin", 44128.12, 875.1, 875000000000),
        Quote(5, "Bitcoin", 44128.12, 875.1, 875000000000),
        Quote(6, "Bitcoin", 44128.12, 875.1, 875000000000),
        Quote(7, "Bitcoin", 44128.12, 875.1, 875000000000),
        Quote(8, "Bitcoin", 44128.12, 875.1, 875000000000),
        Quote(9, "Bitcoin", 44128.12, 875.1, 875000000000),
        Quote(10, "Bitcoin", 44128.12, 875.1, 875000000000),
        Quote(10, "Bitcoin", 44128.12, 875.1, 875000000000),
        Quote(11, "Bitcoin", 44128.12, 875.1, 875000000000),
        Quote(12, "Bitcoin", 44128.12, 875.1, 875000000000),
        Quote(13, "Bitcoin", 44128.12, 875.1, 875000000000),
        Quote(14, "Bitcoin", 44128.12, 875.1, 875000000000),
        Quote(15, "Bitcoin", 44128.12, 875.1, 875000000000),
        Quote(16, "Bitcoin", 44128.12, 875.1, 875000000000),
        Quote(17, "Bitcoin", 44128.12, 875.1, 875000000000),
    )


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentQuotesBinding.inflate(inflater, container, false)

        val root: View = binding.root

        //находим наш RV
        binding.quotesRecycler.apply {
            //Инициализируем наш адаптер в конструктор передаем анонимно инициализированный интерфейс,
            //оставим его пока пустым, он нам понадобится во второй части задания
            quotesAdapter = QuoteRecyclerAdapter(object : QuoteRecyclerAdapter.OnItemClickListener {
                override fun click(quote: Quote) {
                    TODO("Not yet implemented")
                }
            })
            //Присваиваем адаптер
            adapter = quotesAdapter
            //Присваиваем layoutmanager
            layoutManager = LinearLayoutManager(requireContext())
        }
        //Кладем нашу БД в RV
        quotesAdapter.addItems(quotesDataBase)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}