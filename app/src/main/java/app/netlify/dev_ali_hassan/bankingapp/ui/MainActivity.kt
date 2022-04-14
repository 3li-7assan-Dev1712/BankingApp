package app.netlify.dev_ali_hassan.bankingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import app.netlify.dev_ali_hassan.bankingapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/**
 * This is the main activity of the app which contains all of the fragmetns of the app
 * using the navigiation component library it will organize the fragments to make
 * the app screens, and therefore this will be the only activity of the app beside
 * the SplashScreen activity.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /**
     * instantiate the navigation controller to be used in navigating between different
     * destinations (fragments) for example we use a method like (findNavController())
     * then we call the method (navigate(fragmentId)) to make the navigation.
     */
    private lateinit var navController: NavController


    /**
     * in the onCreate function we instantiate the navController with nav graph that
     * we have it in the navigation resources.
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                as NavHostFragment
        navController = navHostFragment.findNavController()
        setupActionBarWithNavController(navController)
        navController.restoreState(savedInstanceState)
    }


    /**
     * overriding this function will give the app the ability of controlling the home back button
     * that will be appear at the top-left corner in the screens.
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}