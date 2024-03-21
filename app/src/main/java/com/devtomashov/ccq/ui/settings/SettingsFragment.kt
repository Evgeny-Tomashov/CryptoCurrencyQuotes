package com.devtomashov.ccq.ui.settings

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.devtomashov.ccq.R
import com.devtomashov.ccq.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var switchTheme: Switch
    private lateinit var settingsTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val settingsViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        settingsViewModel.settings.observe(viewLifecycleOwner) {
        }
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        switchTheme = requireView().findViewById(R.id.switch_theme)
        settingsTextView = requireView().findViewById(R.id.settings_textview)
        switchTheme.isChecked = getSavedThemeState()

        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setDarkTheme()
            } else {
                setLightTheme()
            }
            saveThemeState(isChecked)
        }
        if (switchTheme.isChecked){
            setDarkTheme()
        } else {
            setLightTheme()
        }
    }
    private fun setLightTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun setDarkTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    private fun getSavedThemeState(): Boolean {
        val sharedPreference =
            requireActivity().getSharedPreferences("ThemePrefs", Context.MODE_PRIVATE)
        return sharedPreference.getBoolean("isDarkTheme", false)
    }

    private fun saveThemeState(isDarkTheme: Boolean) {
        val sharedPreference =
            requireActivity().getSharedPreferences("ThemePrefs", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putBoolean("isDarkTheme", isDarkTheme)
        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        val textColor = if (switchTheme.isChecked)
            R.color.white
        else
            R.color.black
        settingsTextView.setTextColor(ContextCompat.getColor(requireContext(), textColor))
        switchTheme.setTextColor(ContextCompat.getColor(requireContext(), textColor))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}