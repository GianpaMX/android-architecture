package io.github.gianpamx.android.architecture.data.retrofit

import io.github.gianpamx.android.architecture.data.ImagesGateway
import io.github.gianpamx.android.architecture.entity.ApiException

class RetrofitImages(val imgurService: ImgurService) : ImagesGateway {
    companion object {
        val TAG = "RetrofitImages"
    }

    override fun getAlbum(albumHash: String): List<String> {
        val call = imgurService.album(albumHash)
        val response = call.execute()

        if (!response.isSuccessful) {
            throw ApiException()
        }

        return response.body()?.data?.map { it.toImage() } ?: emptyList()
    }
}

private fun ImageDataModel.toImage() = link
