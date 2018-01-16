package io.github.gianpamx.android.architecture.usecase

interface GetImagesUseCase {
    fun execute(success: (images: List<String>) -> Unit)
}