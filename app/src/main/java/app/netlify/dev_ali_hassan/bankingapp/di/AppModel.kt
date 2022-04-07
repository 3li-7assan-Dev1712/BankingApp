package app.netlify.dev_ali_hassan.bankingapp.di

import android.app.Application
import androidx.room.Room
import app.netlify.dev_ali_hassan.bankingapp.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModel {

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application) =
        Room.databaseBuilder(app, AppDatabase::class.java, "bank_database")
            .createFromAsset("database/bank_database.db")
            .build()

    @Provides
    fun provideCustomersDao(appDatabase: AppDatabase) =
        appDatabase.customersDao()

    @Provides
    fun provideTransformationsDao(appDatabase: AppDatabase) =
        appDatabase.transformationsDao()
}