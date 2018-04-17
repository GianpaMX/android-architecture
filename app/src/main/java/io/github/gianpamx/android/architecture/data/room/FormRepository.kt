package io.github.gianpamx.android.architecture.data.room

import io.github.gianpamx.android.architecture.data.FormGateway
import io.github.gianpamx.android.architecture.entity.Form

class FormRepository(val formDao: FormDao) : FormGateway {
    override fun findForm() = formDao.findForm()?.toForm()

    override fun persist(form: Form) {
        formDao.insert(form.toFormDao())
    }
}

internal fun FormRoom.toForm() = Form(
        name = name,
        phone = phone
)

private fun Form.toFormDao() = FormRoom(
        name = name,
        phone = phone
)
