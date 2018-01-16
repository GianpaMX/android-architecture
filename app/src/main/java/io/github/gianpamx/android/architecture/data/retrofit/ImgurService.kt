package io.github.gianpamx.android.architecture.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ImgurService {
    @Headers("Authorization: Client-ID e64c31050a6a35b")
    @GET("album/{albumHash}/images")
    fun album(@Path("albumHash") albumHash: String): Call<AlbumDataModel>
}
