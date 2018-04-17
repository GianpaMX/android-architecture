package io.github.gianpamx.android.architecture.data

import io.github.gianpamx.android.architecture.entity.Form

interface FormGateway {
    fun persist(form: Form)
    fun findForm(): Form?
}
