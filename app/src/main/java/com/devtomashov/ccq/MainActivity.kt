package com.devtomashov.ccq

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.devtomashov.ccq.data.entity.Quote
import com.devtomashov.ccq.databinding.ActivityMainBinding
import com.devtomashov.ccq.ui.converter.ConverterFragment
import com.devtomashov.ccq.ui.details.DetailsFragment
import com.devtomashov.ccq.ui.favourites.FavouritesFragment
import com.devtomashov.ccq.ui.quotes.QuotesFragment

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        initNavigation()

        //Запускаем фрагмент при старте
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, QuotesFragment())
            .addToBackStack(null)
            .commit()

    }

    fun launchDetailsFragment(quote: Quote) {
        //Создаем "посылку"
        val bundle = Bundle()
        //Кладем котировку в "посылку"
        bundle.putParcelable("quote", quote)
        //Кладем фрагмент с деталями в перменную
        val fragment = DetailsFragment()
        //Прикрепляем нашу "посылку" к фрагменту
        fragment.arguments = bundle

        //Запускаем фрагмент
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun initNavigation() {

        binding?.bottomNavigation?.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.navigation_quotes -> {
                    val tag = "navigation_quotes"
                    val fragment = checkFragmentExistence(tag)
                    //В первом параметре, если фрагмент не найден и метод вернул null, то с помощью
                    //элвиса мы вызываем создание нового фрагмента
                    changeFragment(fragment ?: QuotesFragment(), tag)
                    true
                }

                R.id.navigation_converter -> {
                    val tag = "favorites"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment ?: ConverterFragment(), tag)
                    true
                }

                R.id.navigation_favourites -> {
                    val tag = "watch_later"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment ?: FavouritesFragment(), tag)
                    true
                }

                else -> false
            }
        }
    }

    //Ищем фрагмент по тэгу, если он есть то возвращаем его, если нет - то null
    private fun checkFragmentExistence(tag: String): Fragment? =
        supportFragmentManager.findFragmentByTag(tag)

    private fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment, tag)
            .addToBackStack(null)
            .commit()
    }
}