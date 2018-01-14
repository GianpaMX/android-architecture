package io.github.gianpamx.android.architecture.usecase

class SaveFormUseCaseImpl : SaveFormUseCase {
    override fun execute(name: String, phone: String, success: () -> Unit) {
        success()
    }
}
