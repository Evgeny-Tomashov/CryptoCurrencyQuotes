package com.devtomashov.ccq.ui.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.devtomashov.ccq.R
import com.devtomashov.ccq.domain.Quote
import com.devtomashov.ccq.databinding.FragmentQuotesBinding
import com.devtomashov.ccq.ui.rv_adapters.QuoteRecyclerAdapter
import java.util.Locale

@Suppress("UNREACHABLE_CODE")
class QuotesFragment : Fragment() {
    private val quotesViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(QuotesFragmentViewModel::class.java)
    }

    private var _binding: FragmentQuotesBinding? = null
    private lateinit var quotesAdapter: QuoteRecyclerAdapter
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

        initSearchView()
        initRecycler()

        //Кладем нашу БД в RV
        quotesViewModel.quotesListLiveData.observe(viewLifecycleOwner) {
            quotesDataBase = it
            quotesAdapter.addItems(it)
        }
    }

    private fun initSearchView() {
        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
        }

        //Подключаем слушателя изменений введенного текста в поиска
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            //Этот метод отрабатывает при нажатии кнопки "поиск" на софт клавиатуре
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            //Этот метод отрабатывает на каждое изменения текста
            override fun onQueryTextChange(newText: String): Boolean {
                //Если ввод пуст то вставляем в адаптер всю БД
                if (newText.isEmpty()) {
                    quotesAdapter.addItems(quotesDataBase)
                    return true
                }
                //Фильтруем список на поиск подходящих сочетаний
                val result = quotesDataBase.filter {
                    //Чтобы все работало правильно, нужно и запроси и имя фильма приводить к нижнему регистру
                    it.nameCC.toLowerCase(Locale.getDefault())
                        .contains(newText.toLowerCase(Locale.getDefault()))
                }
                //Добавляем в адаптер
                quotesAdapter.addItems(result)
                return true
            }
        })
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