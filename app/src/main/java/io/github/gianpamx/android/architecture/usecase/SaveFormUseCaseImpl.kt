package io.github.gianpamx.android.architecture.usecase

import io.github.gianpamx.android.architecture.data.FormGateway
import io.github.gianpamx.android.architecture.entity.Form

class SaveFormUseCaseImpl(val formGateway: FormGateway) : SaveFormUseCase {
    override fun execute(name: String, phone: String, success: () -> Unit) {
        formGateway.persist(Form(name, phone), success)
    }
}
