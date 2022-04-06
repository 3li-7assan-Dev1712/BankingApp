package app.netlify.dev_ali_hassan.bankingapp.ui.transfermoneydialog

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import app.netlify.dev_ali_hassan.bankingapp.R
import app.netlify.dev_ali_hassan.bankingapp.data.models.Customer
import app.netlify.dev_ali_hassan.bankingapp.databinding.TransferMoneyDialogBinding

class TransferMoneyDialog : DialogFragment(R.layout.transfer_money_dialog) {


    private lateinit var binding: TransferMoneyDialogBinding

    private var customer: Customer? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = TransferMoneyDialogBinding.bind(view)
        binding.transferMoneyTextView.setOnClickListener {
            val amount = binding.moneyAmountEditText.text.toString()
            if (amount.isEmpty()) {
                binding.moneyAmountEditText.setError(getString(R.string.edit_text_cannot_be_empty))
            } else {

                customer?.also {
                    transferMoneyToCustomer(amount, it)
                }

            }
        }
    }

    private fun transferMoneyToCustomer(amount: String, customer: Customer) {
        Toast.makeText(
            requireContext(),
            "Transfer $$amount to the ${customer.customerName}",
            Toast.LENGTH_LONG
        )
            .show()
        findNavController().popBackStack()
    }

    companion object {
        const val TAG = "TransferMoneyDialog"
    }
}