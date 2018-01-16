package io.github.gianpamx.android.architecture.data.retrofit

import dagger.Module
import dagger.Provides
import io.github.gianpamx.android.architecture.data.ImagesGateway
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class RetrofitModule {
    @Provides
    @Singleton
    fun provideImagesGateway(imgurService: ImgurService): ImagesGateway = RetrofitImages(imgurService)

    @Provides
    @Singleton
    fun provideImgurService(retrofit: Retrofit) = retrofit.create<ImgurService>(ImgurService::class.java!!)

    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://api.imgur.com/3/").build()
}
