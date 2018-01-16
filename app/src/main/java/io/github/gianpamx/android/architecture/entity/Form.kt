package io.github.gianpamx.android.architecture.entity

class Form {
    var name: String?
    var phone: String?

    constructor() : this(null, null)

    constructor(name: String?, phone: String?) {
        this.name = name
        this.phone = phone
    }
}