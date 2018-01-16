package io.github.gianpamx.android.architecture.usecase

import io.github.gianpamx.android.architecture.data.FormGateway
import io.github.gianpamx.android.architecture.entity.Form

class GetFormUseCaseImpl(val formGateway: FormGateway) : GetFormUseCase {
    override fun execute(success: (form: Form) -> Unit) {
        formGateway.findForm(success)
    }
}
