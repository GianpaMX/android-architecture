package io.github.gianpamx.android.architecture.usecase

import io.github.gianpamx.android.architecture.data.ImagesGateway
import kotlin.concurrent.thread

interface GetImagesUseCase {
    fun execute(success: ((images: List<String>) -> Unit)? = null, failure: ((Throwable) -> Unit)? = null)
}

class GetImagesUseCaseImpl(private val imagesGateway: ImagesGateway) : GetImagesUseCase {
    override fun execute(success: ((images: List<String>) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        thread {
            try {
                val images = executeSync()
                success?.invoke(images)
            } catch (t: Throwable) {
                failure?.invoke(t)
            }
        }
    }

    internal fun executeSync() = imagesGateway.getAlbum("aroSU")
}
