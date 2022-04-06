package app.netlify.dev_ali_hassan.bankingapp.ui.allcustomers

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import app.netlify.dev_ali_hassan.bankingapp.R
import dagger.hilt.android.AndroidEntryPoint


/**
 *
 * The Fragment will be display all customer in a list back-ended be the AllCustomersViewModel
 */
@AndroidEntryPoint
class AllCustomersFragment : Fragment(R.layout.all_customers_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated: starts")
        // will be filled in the up coming commits

    }

    companion object {
        const val TAG = "AllCustomersFragment"
    }
}