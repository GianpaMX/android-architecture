package io.github.gianpamx.android.architecture.usecase

interface SaveFormUseCase {
    fun execute(name: String?, phone: String?, success: () -> Unit, failure: (throwable: Throwable) -> Unit)
}

