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

    /**
     * The main view model is responsible of providing interaction with the datastore asynchronously
     * it will be accessible to all the screen fragment and that is why it is located under the ui
     * package.
     *
     */

    // a flow of the dark theme option
    val darkThemePreferences = preferencesManager.preferencesFlow

    /**
     * This method will update the value of the dark theme option in the data store
     *
     * @param isDarkTheme a boolean type to be used for updating the data in the datastore.
     */
    fun updateDarkTheme(isDarkTheme: Boolean) {
        viewModelScope.launch {
            preferencesManager.enableDisableDarkTheme(isDarkTheme)
        }
    }
}