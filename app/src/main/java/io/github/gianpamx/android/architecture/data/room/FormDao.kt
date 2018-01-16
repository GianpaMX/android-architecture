package io.github.gianpamx.android.architecture.data.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE

@Dao
interface FormDao {
    @Insert(onConflict = REPLACE)
    fun insert(formRoom: FormRoom)
}
