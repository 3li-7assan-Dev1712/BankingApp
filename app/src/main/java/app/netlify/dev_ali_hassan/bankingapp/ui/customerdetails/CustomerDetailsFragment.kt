package app.netlify.dev_ali_hassan.bankingapp.ui.customerdetails

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.netlify.dev_ali_hassan.bankingapp.R
import app.netlify.dev_ali_hassan.bankingapp.data.models.Customer
import app.netlify.dev_ali_hassan.bankingapp.databinding.CustomerDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CustomerDetailsFragment : Fragment(R.layout.customer_details_fragment) {

    private lateinit var binding: CustomerDetailsFragmentBinding

    private val viewModel: CustomerDetailsViewModel by viewModels()

    private var customer: Customer? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = CustomerDetailsFragmentBinding.bind(view)
        customer = arguments?.getParcelable("selected_customer")
        customer?.apply {
            binding.detailsCustomerNameTv.text = this.customerName
            binding.detailsCustomerEmailTv.text = this.customerEmail
            binding.detailsCustomerAvailableBalance.text = this.customerBankAmount.toString()
            var gender = ""
            if (customer?.customerGenderIsMale == true)
                gender = "male"
            else
                gender = "female"
            binding.detailsCustomerGender.text = gender
            binding.detailscustomerBankId.text = this.customerBankId

        }

        // listener
        binding.transferMoneyBtn.setOnClickListener {
            // notify the view model the user clicks transfer money button
            viewModel.userClickTransferMoneyBtn()
        }

        listenToOrdersFromViewModel()
    }

    private fun listenToOrdersFromViewModel() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.eventsFlow.collect { events ->
                when (events) {
                    is CustomerDetailsViewModel.CustomerDetailsEvents.DisplayAmountMoneyDialog -> {
                        // display dialog fragment to ask the customer how much they want to
                        // transfer
                        customer?.also {
                            val data = bundleOf("customer" to it)
                            findNavController().navigate(
                                R.id.action_customerDetailsFragment_to_transferMoneyDialog,
                                data
                            )
                        }

                    }
                }
            }
        }
    }
}