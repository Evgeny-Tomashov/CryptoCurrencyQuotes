package com.devtomashov.ccq.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.devtomashov.ccq.MainActivity
import com.devtomashov.ccq.data.entity.Quote
import com.devtomashov.ccq.databinding.FragmentFavouritesBinding
import com.devtomashov.ccq.ui.rv_adapters.QuoteRecyclerAdapter

class FavouritesFragment : Fragment() {

    private lateinit var favAdapter: QuoteRecyclerAdapter
    private var _binding: FragmentFavouritesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val favouritesViewModel =
            ViewModelProvider(this).get(FavouritesViewModel::class.java)

        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        val root: View = binding.root


        favouritesViewModel.favourite.observe(viewLifecycleOwner) {

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Получаем список при транзакции фрагмента
        val favouritesList: List<Quote> = emptyList()

        initRecyclerFav()

        //Кладем нашу БД в RV
        favAdapter.addItems(favouritesList)
    }

    private fun initRecyclerFav() {
        //находим наш RV
        binding.favouritesRecycler.apply {
            //Инициализируем наш адаптер
             favAdapter = QuoteRecyclerAdapter(object : QuoteRecyclerAdapter.OnItemClickListener {
                override fun click(quote: Quote) {
                    (requireActivity() as MainActivity).launchDetailsFragment(quote)
                }
            })
            //Присваиваем адаптер
            adapter = favAdapter
            //Присваиваем layoutmanager
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}