package app.netlify.dev_ali_hassan.bankingapp.ui.allcustomers

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.netlify.dev_ali_hassan.bankingapp.R
import app.netlify.dev_ali_hassan.bankingapp.data.models.Customer
import app.netlify.dev_ali_hassan.bankingapp.databinding.AllCustomersFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


/**
 *
 * The Fragment will be display all customer in a list back-ended be the AllCustomersViewModel
 */
@AndroidEntryPoint
class AllCustomersFragment : Fragment(R.layout.all_customers_fragment), AllCustomerIsAdapter.OnCustomerSelected {

    private lateinit var binding: AllCustomersFragmentBinding
    private val viewModel: AllCustomersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AllCustomersFragmentBinding.bind(view)
        val adapter = AllCustomerIsAdapter(this, requireContext())
        binding.allCustoemrsRv.adapter = adapter
        adapter.submitList(viewModel.provideTempData())
        Log.d(TAG, "onViewCreated: starts")

        // listen to events
        listenToEvents()
    }


    private fun listenToEvents() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.eventsFlow.collect {events ->
                when (events) {
                    is AllCustomersViewModel.AllCustomerEvents.NavigateToDetailsFragment -> {
                        navigateToDetailsFragment(events.customer)
                    }
                }
            }
        }
    }

    private fun navigateToDetailsFragment(customer: Customer) {
        val data = bundleOf("selected_customer" to customer)
        findNavController().navigate(R.id.action_allCustomersFragment_to_customerDetailsFragment, data)
    }


    companion object {
        const val TAG = "AllCustomersFragment"
    }

    override fun onCustomerSelected(customer: Customer) {
        viewModel.userSelectCustomer(customer)
    }
}