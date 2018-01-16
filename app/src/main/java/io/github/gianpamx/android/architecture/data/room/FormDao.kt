package io.github.gianpamx.android.architecture.data.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface FormDao {
    @Insert(onConflict = REPLACE)
    fun insert(formRoom: FormRoom)

    @Query("SELECT * FROM FormRoom LIMIT 1")
    fun findForm(): FormRoom?
}
