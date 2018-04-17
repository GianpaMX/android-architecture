package io.github.gianpamx.android.architecture.data.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class FormRoom {
    @PrimaryKey
    var uid: Int? = null

    var name: String

    var phone: String

    constructor(name: String = "", phone: String = "") {
        this.name = name
        this.phone = phone
    }
}
