package app.netlify.dev_ali_hassan.bankingapp.data.models

enum class Gender { MALE, FEMALE }
data class Customer(
    val customerName: String,
    val customerBankId: String,
    val customerBankAmount: Int,
    val customerEmail: String,
    var customerAvatarCode: Int = 0,
    val customerGender: Gender
) {
    init {
        customerAvatarCode = when (customerGender) {
            Gender.MALE -> {
                (1..3).random()
            }
            else -> {
                (4..6).random()
            }
        }
    }
}


