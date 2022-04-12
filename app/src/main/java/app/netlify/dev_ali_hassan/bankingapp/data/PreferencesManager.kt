package app.netlify.dev_ali_hassan.bankingapp.data

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.emptyPreferences
import androidx.datastore.preferences.preferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(@ApplicationContext context: Context) {

    private val datastore = context.createDataStore("dark_theme_preference")

    val preferencesFlow = datastore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "error reading data: ${it.message}")
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[DARK_MODE_ENABLED] ?: false
        }


    suspend fun enableDisableDarkTheme(enabled: Boolean) {
        datastore.edit { preferences ->
            preferences[DARK_MODE_ENABLED] = enabled
        }
    }


    companion object{
        const val TAG = "PreferencesManager"
        val DARK_MODE_ENABLED = preferencesKey<Boolean>("dark_mode_enabled")

    }

}