package app.netlify.dev_ali_hassan.bankingapp.ui.transformations

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.netlify.dev_ali_hassan.bankingapp.R
import app.netlify.dev_ali_hassan.bankingapp.data.models.Transformation
import app.netlify.dev_ali_hassan.bankingapp.databinding.TrasnformationListItemBinding


class TransformationsAdapter(
    val context: Context
) :
    ListAdapter<Transformation, TransformationsAdapter.TransformationsViewHolder>(
        TransformationDiffUtil()
    ) {

    inner class TransformationsViewHolder(val binding: TrasnformationListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            trasnformation: Transformation
        ) {
            binding.customerNameTv.text = trasnformation.transformerName
            binding.customerBankIdTv.text = trasnformation.transformerBankId
            binding.customerMoneyAmountTv.text = trasnformation.balance.toString()
            if (trasnformation.isReceived)
                binding.moneyStatusImageView.setImageResource(R.drawable.ic_received)
            else
                binding.moneyStatusImageView.setImageResource(R.drawable.ic_send)
        }
    }


    class TransformationDiffUtil : DiffUtil.ItemCallback<Transformation>() {
        override fun areItemsTheSame(
            oldCustomer: Transformation,
            newCustomer: Transformation
        ): Boolean =
            oldCustomer.transformerBankId == newCustomer.transformerBankId

        override fun areContentsTheSame(
            oldCustomer: Transformation,
            newCustomer: Transformation
        ): Boolean =
            oldCustomer == newCustomer
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransformationsViewHolder {
        val binding =
            TrasnformationListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return TransformationsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransformationsViewHolder, position: Int) {
        val transformation = getItem(position)
        holder.bind(transformation)
    }

}