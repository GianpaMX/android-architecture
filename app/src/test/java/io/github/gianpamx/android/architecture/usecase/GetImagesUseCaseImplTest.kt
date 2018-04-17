package io.github.gianpamx.android.architecture.usecase

import com.nhaarman.mockito_kotlin.whenever
import io.github.gianpamx.android.architecture.data.ImagesGateway
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetImagesUseCaseImplTest {
    @Mock
    lateinit var imagesGateway: ImagesGateway

    lateinit var getImagesUseCaseImpl: GetImagesUseCaseImpl

    @Before
    fun setUp() {
        getImagesUseCaseImpl = GetImagesUseCaseImpl(imagesGateway)
    }

    @Test
    fun getImagesSuccessfully() {
        val expectedImages = listOf("ANY_IMAGE_1", "ANY_IMAGE_2")
        whenever(imagesGateway.getAlbum(anyString())).thenReturn(expectedImages)

        val images = getImagesUseCaseImpl.executeSync()

        assertEquals(expectedImages, images)
    }
}
