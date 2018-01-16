package io.github.gianpamx.android.architecture.data.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class FormRoom {
    constructor(name: String? = null, phone: String? = null) {
        this.name = name
        this.phone = phone
    }

    @PrimaryKey
    var uid: Int? = null

    @ColumnInfo(name = "name")
    var name: String?

    @ColumnInfo(name = "phone")
    var phone: String?
}
