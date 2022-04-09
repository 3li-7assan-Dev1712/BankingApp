package app.netlify.dev_ali_hassan.bankingapp.util

import android.content.Context
import java.text.NumberFormat
import java.util.*

class ResourceUtil {
    companion object {
        private val currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault())

        fun getFormattedCurrency(amount: Int) =
            currencyFormatter.format(amount)

        fun getResourceIdOfAvatar(avatarCode: Int, context: Context): Int =
            context.resources.getIdentifier("avatar_$avatarCode", "drawable", context.packageName)


    }
}