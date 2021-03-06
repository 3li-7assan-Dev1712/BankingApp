package app.netlify.dev_ali_hassan.bankingapp.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import app.netlify.dev_ali_hassan.bankingapp.data.models.Transformation
import kotlinx.coroutines.flow.Flow

/**
 * This is an interface to provide funtions to talk with the Room Database
 */

@Dao
interface TransformationsDao {

    @Query ("SELECT * FROM transformations ORDER BY transformationTimestamp DESC")
    fun getAllTransformations(): Flow<List<Transformation>>

    @Insert
    suspend fun addNewTransformation(transformation: Transformation)
}