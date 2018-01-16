package io.github.gianpamx.android.architecture.data.retrofit

import com.google.gson.annotations.SerializedName

class AlbumDataModel {
    @SerializedName("data")
    var data = ArrayList<ImageDataModel>()
}

