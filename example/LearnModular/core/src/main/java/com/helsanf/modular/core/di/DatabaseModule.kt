package com.helsanf.modular.core.di

import android.content.Context
import androidx.room.Room
import com.helsanf.modular.core.data.source.local.room.SportDao
import com.helsanf.modular.core.data.source.local.room.SportDatabases
import dagger.Module
import dagger.Provides
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
class DatabaseModule{
    val passphrase: ByteArray = SQLiteDatabase.getBytes("learnmodular".toCharArray())
    val factory = SupportFactory(passphrase)

    @Singleton
    @Provides
    fun provideDatabase(context: Context) : SportDatabases = Room.databaseBuilder(
        context,
        SportDatabases::class.java,
        "Sport.db"
    ).fallbackToDestructiveMigration().openHelperFactory(factory).build()

    @Provides
    fun provideDao(database: SportDatabases) : SportDao = database.sportDao()
}