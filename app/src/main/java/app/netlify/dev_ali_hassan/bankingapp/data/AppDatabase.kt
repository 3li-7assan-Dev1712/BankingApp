package app.netlify.dev_ali_hassan.bankingapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import app.netlify.dev_ali_hassan.bankingapp.data.daos.CustomerDao
import app.netlify.dev_ali_hassan.bankingapp.data.daos.TransformationsDao
import app.netlify.dev_ali_hassan.bankingapp.data.models.Customer
import app.netlify.dev_ali_hassan.bankingapp.data.models.Transformation

@Database(entities = [Customer::class, Transformation::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customersDao(): CustomerDao
    abstract fun transformationsDao(): TransformationsDao
}
