package com.devtomashov.ccq.ui.converter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConverterViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
    }
    val converter: LiveData<String> = _text
}