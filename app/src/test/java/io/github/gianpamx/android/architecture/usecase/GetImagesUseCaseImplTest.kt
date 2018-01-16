package io.github.gianpamx.android.architecture.usecase

import com.nhaarman.mockito_kotlin.*
import io.github.gianpamx.android.architecture.data.ImagesGateway
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetImagesUseCaseImplTest {
    companion object {
        val EXPECTED_IMAGES = emptyList<String>()
    }

    val imagesGateway = mock<ImagesGateway>()

    lateinit var getImagesUseCase: GetImagesUseCase

    @Before
    fun setUp() {
        getImagesUseCase = GetImagesUseCaseImpl(imagesGateway)
    }

    @Test
    fun execute() {
        val callback = mock<(images: List<String>) -> Unit>()
        val captor = argumentCaptor<(images: List<String>) -> Unit>()

        getImagesUseCase.execute(callback)

        verify(imagesGateway).getAlbum(any(), captor.capture())
        captor.firstValue.invoke(EXPECTED_IMAGES)
        verify(callback).invoke(eq(EXPECTED_IMAGES))
    }
}
