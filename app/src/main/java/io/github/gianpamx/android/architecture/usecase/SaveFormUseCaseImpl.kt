package io.github.gianpamx.android.architecture.usecase

import io.github.gianpamx.android.architecture.data.FormGateway
import io.github.gianpamx.android.architecture.entity.EmptyNameExeption
import io.github.gianpamx.android.architecture.entity.EmptyPhoneExeption
import io.github.gianpamx.android.architecture.entity.Form

class SaveFormUseCaseImpl(val formGateway: FormGateway) : SaveFormUseCase {
    override fun execute(name: String?, phone: String?, success: () -> Unit, failure: (throwable: Throwable) -> Unit) {
        if (stringOf(name).isNullOrEmpty()) {
            failure.invoke(EmptyNameExeption())
            return
        }

        if (stringOf(phone).isNullOrEmpty()) {
            failure.invoke(EmptyPhoneExeption())
            return
        }

        formGateway.persist(Form(name, phone), success)
    }

    private fun stringOf(string: String?) = string ?: ""
}
