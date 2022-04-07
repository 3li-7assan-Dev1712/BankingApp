package app.netlify.dev_ali_hassan.bankingapp.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Entity(tableName = "customers")
@Parcelize
data class Customer(
    val customerName: String,
    val customerBankId: String,
    val customerBankAmount: Int,
    val customerEmail: String,
    var customerAvatarCode: Int = 0,
    val customerGenderIsMale: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
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


