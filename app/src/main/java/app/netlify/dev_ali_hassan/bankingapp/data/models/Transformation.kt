package app.netlify.dev_ali_hassan.bankingapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transformations")
data class Transformation(
    val transformerName: String,
    val transformationTimestamp: Long = System.currentTimeMillis(),
    val balance: Int,
    val isReceived: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)