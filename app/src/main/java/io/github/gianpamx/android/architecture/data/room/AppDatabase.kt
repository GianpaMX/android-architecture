package io.github.gianpamx.android.architecture.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(FormRoom::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun formDao(): FormDao
}
