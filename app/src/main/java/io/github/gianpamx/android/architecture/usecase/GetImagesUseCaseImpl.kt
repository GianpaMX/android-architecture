package io.github.gianpamx.android.architecture.usecase

import io.github.gianpamx.android.architecture.data.ImagesGateway

class GetImagesUseCaseImpl(val imagesGateway: ImagesGateway) : GetImagesUseCase {
    override fun execute(success: (images: List<String>) -> Unit) {
        imagesGateway.getAlbum("aroSU", success)
    }
}
