package com.example.saranskfinal.data.localizationHelper

import kotlinx.coroutines.flow.MutableStateFlow

object LocalizationHelper {
    var currentLanguage = " "
    val language = MutableStateFlow(currentLanguage)

    fun setLanguage(langCode: String){
        currentLanguage = langCode
        language.value = langCode


    }

}