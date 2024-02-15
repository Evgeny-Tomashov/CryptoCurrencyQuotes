package com.devtomashov.ccq.ui.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.devtomashov.ccq.domain.Quote
import com.devtomashov.ccq.databinding.FragmentQuotesBinding
import com.devtomashov.ccq.ui.rv_adapters.QuoteRecyclerAdapter

class QuotesFragment : Fragment() {

    private var _binding: FragmentQuotesBinding? = null
    private lateinit var quotesAdapter: QuoteRecyclerAdapter
    private val quotesViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(QuotesFragmentViewModel::class.java)
    }
    private var quotesDataBase = listOf<Quote>()
        //Используем backing field
        set(value) {
            //Если придет такое же значение, то мы выходим из метода
            if (field == value) return
            //Если пришло другое значение, то кладем его в переменную
            field = value
            //Обновляем RV адаптер
            quotesAdapter.addItems(field)
        }

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
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        //Кладем нашу БД в RV
        quotesViewModel.quotesListLiveData.observe(viewLifecycleOwner) {
            quotesDataBase = it
            quotesAdapter.addItems(it)
        }
    }

    private fun initRecycler() {
        //находим наш RV
        binding.quotesRecycler.apply {
            //Инициализируем наш адаптер в конструктор передаем анонимно инициализированный интерфейс,
            //оставим его пока пустым, он нам понадобится позже
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
    }
}