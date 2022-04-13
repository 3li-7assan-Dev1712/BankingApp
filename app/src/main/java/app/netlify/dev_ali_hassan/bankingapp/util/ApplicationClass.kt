package app.netlify.dev_ali_hassan.bankingapp.util

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import app.netlify.dev_ali_hassan.bankingapp.data.PreferencesManager
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.flow.collect

import javax.inject.Inject
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.suspendCoroutine


@HiltAndroidApp
class ApplicationClass:
    Application() {

    @Inject lateinit var preferencesManager: PreferencesManager

    override fun onCreate() {
        super.onCreate()
        MainScope().launch {
            Log.d(TAG, "onCreate: works from the application class *_* ")
            preferencesManager.preferencesFlow.collect {
                if (it)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                else
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            }
        }
    }
    companion object{
        const val TAG = "ApplicationClass"
    }

}