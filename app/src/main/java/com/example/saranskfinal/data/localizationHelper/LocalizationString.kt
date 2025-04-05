package com.example.saranskfinal.data.localizationHelper

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

@Composable
fun StringLocalization(@StringRes id: Int) : String{

    val currentLang = LocalizationHelper.language.collectAsState().value
    val context = LocalContext.current

    val configuration = Configuration(context.resources.configuration).apply {
        setLocale(Locale(currentLang))
    }

    val localizedString = context.createConfigurationContext(configuration)

    return localizedString.resources.getString(id)

}