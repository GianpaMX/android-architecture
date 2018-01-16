package io.github.gianpamx.android.architecture.data.retrofit

import android.util.Log
import io.github.gianpamx.android.architecture.data.ImagesGateway
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitImages(val imgurService: ImgurService) : ImagesGateway {
    companion object {
        val TAG = "RetrofitImages"
    }

    override fun getAlbum(albumHash: String, success: (images: List<String>) -> Unit) {
        val call = imgurService.album(albumHash)
        call.enqueue(object : Callback<AlbumDataModel?> {
            override fun onResponse(call: Call<AlbumDataModel?>?, response: Response<AlbumDataModel?>?) {
                if (!response?.isSuccessful!!) {
                    Log.d(TAG, response?.errorBody().toString())
                    return
                }

                val imageList = ArrayList<String>()
                for (image in response.body()?.data!!) {
                    imageList.add(image.link)
                }
                success.invoke(imageList)
            }

            override fun onFailure(call: Call<AlbumDataModel?>?, t: Throwable?) {
                Log.d(TAG, "onFailure", t)
            }
        })
    }
}
