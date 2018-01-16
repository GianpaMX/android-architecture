package io.github.gianpamx.android.architecture.data

interface ImagesGateway {
    fun getAlbum(albumHash: String, success: (images: List<String>) -> Unit)
}
