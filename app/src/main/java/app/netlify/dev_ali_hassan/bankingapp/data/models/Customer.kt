package app.netlify.dev_ali_hassan.bankingapp.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Customer(
    val customerName: String,
    val customerBankId: String,
    val customerBankAmount: Int,
    val customerEmail: String,
    var customerAvatarCode: Int = 0,
    val customerGenderIsMale: Boolean
): Parcelable {
    init {
        customerAvatarCode = when (customerGenderIsMale) {
            true -> {
                (1..3).random()
            }
            else -> {
                (4..6).random()
            }
        }
    }
}


