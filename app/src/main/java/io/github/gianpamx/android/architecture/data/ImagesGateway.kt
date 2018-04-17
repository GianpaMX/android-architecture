package io.github.gianpamx.android.architecture.data

interface ImagesGateway {
    fun getAlbum(albumHash: String): List<String>
}
