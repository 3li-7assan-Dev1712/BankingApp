package app.netlify.dev_ali_hassan.bankingapp.ui.allcustomers

import androidx.recyclerview.widget.DiffUtil
import app.netlify.dev_ali_hassan.bankingapp.data.models.Customer

class AllCustomersAdapter {

    interface OnCustomerSelected {
        fun onCustomerSelected()
    }


    class PageDiffUtil : DiffUtil.ItemCallback<Customer>() {
        override fun areItemsTheSame(oldCustomer: Customer, newCustomer: Customer): Boolean =
            oldCustomer.customerBankId == newCustomer.customerBankId

        override fun areContentsTheSame(oldCustomer: Customer, newCustomer: Customer): Boolean =
            oldCustomer == newCustomer
    }

}