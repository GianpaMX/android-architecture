package io.github.gianpamx.android.architecture.data.room

import io.github.gianpamx.android.architecture.data.FormGateway
import io.github.gianpamx.android.architecture.entity.Form
import kotlin.concurrent.thread

class FormRepository(val formDao: FormDao) : FormGateway {
    override fun persist(form: Form, success: () -> Unit) {
        thread(start = true) { persistSync(form, success) }
    }

    fun persistSync(form: Form, success: () -> Unit) {
        val formRoomEntity = FormRoom(form.name, form.phone)
        formDao.insert(formRoomEntity)
        success.invoke()
    }
}
