package com.devtomashov.ccq.ui.details

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.devtomashov.ccq.R
import com.devtomashov.ccq.data.entity.Quote
import com.devtomashov.ccq.databinding.FragmentDetailsBinding
import kotlin.math.roundToInt


@Suppress("DEPRECATION")
class DetailsFragment : Fragment() {

    private lateinit var quote: Quote
    private var _binding: FragmentDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val detailsViewModel =
            ViewModelProvider(this).get(DetailsViewModel::class.java)

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        detailsViewModel.details.observe(viewLifecycleOwner) {

        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setQuotesDetails()

        binding.detailsFabFavorites.setOnClickListener {
            if (!quote.isInFavorites) {
                binding.detailsFabFavorites.setImageResource(R.drawable.baseline_favorite_24)
                quote.isInFavorites = true
            } else {
                binding.detailsFabFavorites.setImageResource(R.drawable.baseline_favorite_border_24)
                quote.isInFavorites = false
            }
        }

        binding.detailsFabShare.setOnClickListener {
            //Создаем интент
            val intent = Intent()
            //Укзываем action с которым он запускается
            intent.action = Intent.ACTION_SEND
            //Кладем данные о нашей котировке
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out this film: ${quote.nameCC} \n\n ${quote.marketCapCC}"
            )
            //УКазываем MIME тип, чтобы система знала, какое приложения предложить
            intent.type = "text/plain"
            //Запускаем наше активити
            startActivity(Intent.createChooser(intent, "Share To:"))
        }
    }

    private fun setQuotesDetails() {
        //Получаем котировку из переданного бандла
        quote = arguments?.get("quote") as Quote
        //Преобразование типа
        val marketCapCCRounded = quote.marketCapCC.toLong()

        //Устанавливаем заголовок
        binding.detailsToolbar.title = quote.nameCC
        //Устанавливаем капитализацию
        binding.marketCapCC.text = marketCapCCRounded.toString()

        binding.detailsFabFavorites.setImageResource(
            if (quote.isInFavorites) R.drawable.baseline_favorite_24
            else R.drawable.baseline_favorite_border_24
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}