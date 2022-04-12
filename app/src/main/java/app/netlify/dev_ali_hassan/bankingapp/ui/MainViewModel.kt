package app.netlify.dev_ali_hassan.bankingapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.netlify.dev_ali_hassan.bankingapp.data.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val preferencesManager: PreferencesManager) :
    ViewModel() {


    val darkThemePreferences = preferencesManager.preferencesFlow

    fun updateDarkTheme(isDarkTheme: Boolean) {
        viewModelScope.launch {
            preferencesManager.enableDisableDarkTheme(isDarkTheme)
        }
    }
}