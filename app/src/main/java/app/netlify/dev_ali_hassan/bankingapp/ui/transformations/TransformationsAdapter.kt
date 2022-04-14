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
import app.netlify.dev_ali_hassan.bankingapp.util.ResourceUtil
import java.text.SimpleDateFormat
import java.util.*

/**
 * An adapter that will be used with the RecyclerView to adapt the data in items in a list.
 *
 * @param context to be used in getting android resources like drawables
 */
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
            binding.customerBankIdTv.text = SimpleDateFormat(
                "h:mm a",
                Locale.getDefault()
            ).format(trasnformation.transformationTimestamp)
            binding.customerMoneyAmountTv.text = ResourceUtil.getFormattedCurrency(trasnformation.balance)
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
            oldCustomer.transformationTimestamp == newCustomer.transformationTimestamp

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