package app.netlify.dev_ali_hassan.bankingapp.data.models

data class Transformation(
    val transformerName: String,
    val transformerAvatarCode: Int,
    val transformerBankId: Int,
    val balance: Int,
    val isReceived: Boolean
)