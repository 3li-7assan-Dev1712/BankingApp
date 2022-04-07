package app.netlify.dev_ali_hassan.bankingapp.data.models

data class Transformation(
    val transformerName: String,
    val transformerAvatarCode: Int,
    var transformationTimestamp: Long = 0,
    val balance: Int,
    val isReceived: Boolean
) {
    init {
        transformationTimestamp = System.currentTimeMillis()
    }
}