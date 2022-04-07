package app.netlify.dev_ali_hassan.bankingapp.ui.homescreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.netlify.dev_ali_hassan.bankingapp.R
import app.netlify.dev_ali_hassan.bankingapp.databinding.HomeScreenFragmentBinding

class HomeScreenFragment : Fragment(R.layout.home_screen_fragment) {

    private lateinit var binding: HomeScreenFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = HomeScreenFragmentBinding.bind(view)
        binding.apply {
            viewAllcustomersBtn.setOnClickListener {
                findNavController().navigate(R.id.action_homeScreenFragment_to_allCustomersFragment)
            }

            tranformationsBtn.setOnClickListener {
                // navigate to transformations fragment.
            }
        }
    }
}