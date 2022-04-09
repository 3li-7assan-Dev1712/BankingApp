package app.netlify.dev_ali_hassan.bankingapp.ui.customerdetails

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.netlify.dev_ali_hassan.bankingapp.R
import app.netlify.dev_ali_hassan.bankingapp.data.models.Customer
import app.netlify.dev_ali_hassan.bankingapp.databinding.CustomerDetailsFragmentBinding
import app.netlify.dev_ali_hassan.bankingapp.util.ResourceUtil
import com.google.android.material.snackbar.Snackbar
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
            binding.detailsCustomerAvailableBalance.text =
                ResourceUtil.getFormattedCurrency(this.customerBankAmount)
            var gender = ""
            gender = if (customer?.customerGenderIsMale == true)
                "Male"
            else
                "Female"
            binding.detailsCustomerGender.text = gender
            binding.detailscustomerBankId.text = this.customerBankId
            binding.customerImgView.setImageResource(
                ResourceUtil.getResourceIdOfAvatar(customer!!.customerAvatarCode, requireContext())
            )

        }

        // listener
        binding.transferMoneyBtn.setOnClickListener {
            // notify the view model the user clicks transfer money button
            viewModel.userClickTransferMoneyBtn()
        }

        listenToOrdersFromViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("operations_status") { requestKey, bundle ->
            val result = bundle.getBoolean("operationSuccessful")
            if (result) {
                Snackbar.make(
                    binding.root,
                    getString(R.string.transfer_money_message),
                    Snackbar.LENGTH_LONG
                )
                    .setAction(R.string.check) {
                        findNavController().navigate(R.id.action_customerDetailsFragment_to_transformationsFragment)
                    }.setAnchorView(binding.transferMoneyBtn).show()

            }
            val updatedBalance = bundle.getInt("updated_balance")
            binding.detailsCustomerAvailableBalance.text =
                ResourceUtil.getFormattedCurrency(updatedBalance)

        }
    }

    private fun listenToOrdersFromViewModel() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.eventsFlow.collect { events ->
                when (events) {
                    is CustomerDetailsViewModel.CustomerDetailsEvents.DisplayAmountMoneyDialog -> {
                        // display dialog fragment to ask the customer how much they want to
                        // transfer
                        customer?.also {
                            val data = bundleOf("selected_customer" to it)
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