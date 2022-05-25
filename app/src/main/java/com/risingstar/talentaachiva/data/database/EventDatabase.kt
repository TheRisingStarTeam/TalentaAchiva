package com.risingstar.talentaachiva.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.risingstar.talentaachiva.domain.data.*

@Database(
    entities = [
        Announcements::class,
        Assignments::class,
        Event::class,
        Identity::class,
        Submissions::class
               ],
    version = 1,
    exportSchema = false
)
abstract class EventDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: EventDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): EventDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    EventDatabase::class.java, "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}