package io.github.gianpamx.android.architecture.usecase

import io.github.gianpamx.android.architecture.entity.Form

class GetFormUseCaseImpl : GetFormUseCase {
    override fun execute(success: (form: Form) -> Unit, failure: () -> Unit) {
        success(Form())
    }
}
