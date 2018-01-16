package io.github.gianpamx.android.architecture.usecase

import io.github.gianpamx.android.architecture.entity.Form

interface GetFormUseCase {
    fun execute(success: (form: Form) -> Unit);
}
