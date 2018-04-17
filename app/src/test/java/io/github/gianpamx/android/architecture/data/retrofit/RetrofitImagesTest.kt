package io.github.gianpamx.android.architecture.data.retrofit

import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.mock.Calls

@RunWith(MockitoJUnitRunner::class)
class RetrofitImagesTest {
    @Mock
    lateinit var imgurService: ImgurService

    lateinit var retrofitImages: RetrofitImages

    @Before
    fun setUp() {
        retrofitImages = RetrofitImages(imgurService)
    }

    @Test
    fun getAlbum() {
        whenever(imgurService.album(anyString())).thenReturn(Calls.response(AlbumDataModel()))

        val album = retrofitImages.getAlbum("ANY_ALBUM")

        assertEquals(emptyList<String>(), album)
    }
}
