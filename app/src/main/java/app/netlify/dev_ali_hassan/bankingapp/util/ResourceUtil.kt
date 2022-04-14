package app.netlify.dev_ali_hassan.bankingapp.util

import android.content.Context
import java.text.NumberFormat
import java.util.*

/**
 * This class will be used to implement general functionalities like formatting strings/
 * getting resources and so on...
 */
class ResourceUtil {
    companion object {
        //currency string formatter
        private val currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault())

        /**
         * The method will take a number to format it to a string currency 
         * 
         * @param amount the amount of money to be formatted by displaying the currency
         * beside the number.
         */
        fun getFormattedCurrency(amount: Int) =
            currencyFormatter.format(amount)

        /**
         * This method will take the code (number) of image (1..6)inclusive to get
         * the int resource of the an image in the drawable folder.
         */
        fun getResourceIdOfAvatar(avatarCode: Int, context: Context): Int =
            context.resources.getIdentifier("avatar_$avatarCode", "drawable", context.packageName)


    }
}