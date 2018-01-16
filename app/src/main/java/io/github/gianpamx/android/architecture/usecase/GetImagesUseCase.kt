package io.github.gianpamx.android.architecture.usecase

interface GetImagesUseCase {
    fun execute(param: (images: List<String>) -> Unit)
}