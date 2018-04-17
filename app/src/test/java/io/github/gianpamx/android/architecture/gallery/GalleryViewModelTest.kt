package io.github.gianpamx.android.architecture.gallery

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.isNull
import com.nhaarman.mockito_kotlin.verify
import io.github.gianpamx.android.architecture.entity.Form
import io.github.gianpamx.android.architecture.usecase.GetFormUseCase
import io.github.gianpamx.android.architecture.usecase.GetImagesUseCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


private const val EXPECTED_NAME = "EXPECTED_NAME"
private const val ANY_STRING = "ANY_STRING"

private val EXPECTED_IMAGES = emptyList<String>()

@RunWith(MockitoJUnitRunner::class)
class GalleryViewModelTest {

    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()

    @Mock
    lateinit var getFormUseCase: GetFormUseCase

    @Mock
    lateinit var getImagesUseCase: GetImagesUseCase

    lateinit var galleryViewModel: GalleryViewModel

    @Before
    fun setUp() {
        galleryViewModel = GalleryViewModel(getFormUseCase, getImagesUseCase)
    }

    @Test
    fun greetingName() {
        val captor = argumentCaptor<(form: Form) -> Unit>()

        verify(getFormUseCase).execute(captor.capture(), isNull())
        captor.firstValue.invoke(Form(EXPECTED_NAME, ANY_STRING))
        assertEquals(EXPECTED_NAME, galleryViewModel.name.value)
    }

    @Test
    fun noImages() {
        verify(getImagesUseCase).execute(any(), isNull())
        assertEquals(EXPECTED_IMAGES, galleryViewModel.images.value)
    }

    @Test
    fun images() {
        val captor = argumentCaptor<(images: List<String>) -> Unit>()

        verify(getImagesUseCase).execute(captor.capture(), isNull())
        captor.firstValue.invoke(EXPECTED_IMAGES)
        assertEquals(EXPECTED_IMAGES, galleryViewModel.images.value)
    }
}
