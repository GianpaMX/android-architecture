package io.github.gianpamx.android.architecture.data

import io.github.gianpamx.android.architecture.entity.Form

interface FormGateway {
    fun persist(form: Form, success: () -> Unit)
    fun findForm(success: (form: Form) -> Unit)
}
