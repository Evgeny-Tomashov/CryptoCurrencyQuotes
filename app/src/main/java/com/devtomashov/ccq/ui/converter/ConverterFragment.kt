package com.devtomashov.ccq.ui.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.devtomashov.ccq.R
import com.devtomashov.ccq.databinding.FragmentConverterBinding


class ConverterFragment : Fragment() {

    private var _binding: FragmentConverterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val converterViewModel =
            ViewModelProvider(this).get(ConverterViewModel::class.java)

        _binding = FragmentConverterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        converterViewModel.converter.observe(viewLifecycleOwner) {
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}