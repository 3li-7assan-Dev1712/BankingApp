package app.netlify.dev_ali_hassan.bankingapp.ui.transfermoneydialog

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.netlify.dev_ali_hassan.bankingapp.R
import app.netlify.dev_ali_hassan.bankingapp.data.models.Customer
import app.netlify.dev_ali_hassan.bankingapp.databinding.TransferMoneyDialogBinding
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
                        showSuccessfullMessageAndPopupBack()
                    }
                }
            }
        }
    }

    private fun showSuccessfullMessageAndPopupBack() {
        findNavController().popBackStack()

        Snackbar.make(binding.root, getString(R.string.transfer_money_message), Snackbar.LENGTH_LONG)
            .setAction(R.string.ok) {


            }.show()
    }

    private fun transferMoneyToCustomer(amount: String, customer: Customer) {
        Log.d(TAG, "transferMoneyToCustomer: ")
        val moneyAmount = amount as Int
        if (moneyAmount > customer.customerBankAmount) {
            binding.moneyAmountEditText.setError(getString(R.string.not_enough_balance))
        } else {
            Toast.makeText(
                requireContext(),
                "Transfer $$amount to ${customer.customerName}",
                Toast.LENGTH_LONG
            )
                .show()

            findNavController().popBackStack()
            Log.d(TAG, "transferMoneyToCustomer: should disappear")
        }
    }

    companion object {
        const val TAG = "TransferMoneyDialog"
    }
}