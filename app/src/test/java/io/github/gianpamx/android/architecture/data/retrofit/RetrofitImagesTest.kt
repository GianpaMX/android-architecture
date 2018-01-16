package io.github.gianpamx.android.architecture.data.retrofit

import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import retrofit2.mock.Calls

@RunWith(JUnit4::class)
class RetrofitImagesTest {
    val imgurService = mock<ImgurService>()

    lateinit var retrofitImages: RetrofitImages

    @Before
    fun setUp() {
        retrofitImages = RetrofitImages(imgurService)
    }

    @Test
    fun getAlbum() {
        whenever(imgurService.album(anyString())).thenReturn(Calls.response(AlbumDataModel()))
        val success = mock<(images: List<String>) -> Unit>()

        retrofitImages.getAlbum("ANY_ALBUM", success)

        verify(success).invoke(eq(emptyList()))
    }
}
