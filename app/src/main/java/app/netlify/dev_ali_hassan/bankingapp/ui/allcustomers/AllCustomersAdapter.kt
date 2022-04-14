package app.netlify.dev_ali_hassan.bankingapp.ui.allcustomers

import android.content.Context
import android.icu.number.NumberFormatter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.netlify.dev_ali_hassan.bankingapp.R
import app.netlify.dev_ali_hassan.bankingapp.data.models.Customer
import app.netlify.dev_ali_hassan.bankingapp.databinding.CustomerListItemBinding
import app.netlify.dev_ali_hassan.bankingapp.util.ResourceUtil
import java.text.NumberFormat
import java.util.*

/**
 * An adapter that will be used with the RecyclerView to adapt the data in items in a list.
 *
 * @param listener to listen to click on items event.
 * @param context to be used in getting android resources like drawables
 */
class AllCustomerIsAdapter(
    val listener: OnCustomerSelected,
    val context: Context
) :
    ListAdapter<Customer, AllCustomerIsAdapter.CustomerViewHolder>(PageDiffUtil()) {

    interface OnCustomerSelected {
        fun onCustomerSelected(customer: Customer)
    }



    inner class CustomerViewHolder(val binding: CustomerListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listener.onCustomerSelected(getItem(adapterPosition))
            }
        }

        fun bind(
            customer: Customer
        ) {
            binding.customerNameTv.text = customer.customerName
            binding.customerBankIdTv.text = customer.customerBankId
            binding.customerMoneyAmountTv.text = ResourceUtil.getFormattedCurrency(customer.customerBankAmount)
            binding.transformerImageView.setImageResource(
                ResourceUtil.getResourceIdOfAvatar(
                    customer.customerAvatarCode,
                    context
                )
            )

        }
    }


    class PageDiffUtil : DiffUtil.ItemCallback<Customer>() {
        override fun areItemsTheSame(oldCustomer: Customer, newCustomer: Customer): Boolean =
            oldCustomer.customerBankId == newCustomer.customerBankId

        override fun areContentsTheSame(oldCustomer: Customer, newCustomer: Customer): Boolean =
            oldCustomer == newCustomer
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val binding =
            CustomerListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val customer = getItem(position)
        holder.bind(customer)
    }

}