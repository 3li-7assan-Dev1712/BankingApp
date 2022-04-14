package app.netlify.dev_ali_hassan.bankingapp.data.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import app.netlify.dev_ali_hassan.bankingapp.data.models.Customer
import kotlinx.coroutines.flow.Flow

/**
 * The DAO Data Access Object is an interface that will interact with the database.
 */
@Dao
interface CustomerDao {

    @Query("SELECT * FROM customers")
    fun getAllCustomers(): Flow<List<Customer>>

    @Update
    suspend fun updateCustomer(updatedCustomer: Customer)
}