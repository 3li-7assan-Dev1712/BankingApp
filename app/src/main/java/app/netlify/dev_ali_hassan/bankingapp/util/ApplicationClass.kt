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


/**
 * The application class is the entry point of the app. That is why it is used in the manifest
 * we use it to make the dependency injection as well as to control the dark theme when the
 * user changes its -dark theme- options.
 */
@HiltAndroidApp
class ApplicationClass:
    Application() {

    /*
     * field injection instead of constructor injection because the android framework
     * will not allow of creating an application class with args in its constructor
     * meaning that the application class's constructor should be empty. The preferences manager
     * wiil be used to manage the dark theme options.
     */
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