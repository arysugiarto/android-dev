package com.arysugiarto.jetpackpro.data.repo.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arysugiarto.jetpackpro.data.repo.local.entity.MovieEntity
import com.arysugiarto.jetpackpro.data.repo.local.entity.TvEntity

@Database(
    entities = [MovieEntity::class, TvEntity::class], version = 1, exportSchema = false
)
abstract class DbMovie : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: DbMovie? = null

        fun getInstance(context: Context): DbMovie =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    DbMovie::class.java,
                    "Movies.db"
                ).build()
            }
    }
}