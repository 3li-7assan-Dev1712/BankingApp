package app.netlify.dev_ali_hassan.bankingapp.ui.transfermoneydialog

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.netlify.dev_ali_hassan.bankingapp.R
import app.netlify.dev_ali_hassan.bankingapp.data.models.Customer
import app.netlify.dev_ali_hassan.bankingapp.databinding.TransferMoneyDialogBinding
import app.netlify.dev_ali_hassan.bankingapp.util.ResourceUtil
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class TransferMoneyDialog : DialogFragment(R.layout.transfer_money_dialog) {


    private val viewModel: TranferMoneyViewModel by viewModels()

    private lateinit var binding: TransferMoneyDialogBinding

    private var customer: Customer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = TransferMoneyDialogBinding.bind(view)
        binding.moneyAmountEditText.hint = ResourceUtil.getFormattedCurrency(500)
        customer = arguments?.getParcelable("selected_customer")
        binding.transferMoneyTextView.setOnClickListener {
            Log.d(TAG, "onViewCreated: transfer is clicked")
            val amount = binding.moneyAmountEditText.text.toString()
            if (amount.isEmpty()) {
                binding.moneyAmountEditText.setError(getString(R.string.edit_text_cannot_be_empty))
            } else {

                customer?.also {
                    Log.d(TAG, "onViewCreated: customer is not null it, should transfer money")
                    transferMoneyToCustomer(amount, it)
                }
                Log.d(TAG, "onViewCreated: called transfer money method")

            }
        }
        binding.cancelTransferMoneyTextView.setOnClickListener {
            findNavController().popBackStack()
            Toast.makeText(requireContext(), "cancel operation successfully", Toast.LENGTH_SHORT)
                .show()
        }

        // listen to events
        listentoEventsFromViewModel()
    }

    private fun listentoEventsFromViewModel() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.eventsFlow.collect { events ->
                when (events) {
                    is TranferMoneyViewModel.TransferMoneyEvents.OperationFinishedSuccessfully -> {
                        showSuccessfulMessageAndPopupBack()
                    }
                }
            }
        }
    }

    private fun showSuccessfulMessageAndPopupBack() {
        setFragmentResult("operations_status", bundleOf("operationSuccessful" to true))
        findNavController().popBackStack()
    }

    private fun transferMoneyToCustomer(amount: String, customer: Customer) {
        Log.d(TAG, "transferMoneyToCustomer: ")
        val moneyAmount = amount.toIntOrNull()
        if (moneyAmount != null) {
            if (moneyAmount > customer.customerBankAmount)
                binding.moneyAmountEditText.setError(getString(R.string.not_enough_balance))
            else
                viewModel.userTransferMoneyToOtherCustomer(moneyAmount, customer)
        }
    }

    companion object {
        const val TAG = "TransferMoneyDialog"
    }
}