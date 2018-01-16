package io.github.gianpamx.android.architecture.usecase

import com.nhaarman.mockito_kotlin.*
import io.github.gianpamx.android.architecture.data.FormGateway
import org.junit.Before
import org.junit.Test

class SaveFormUseCaseImplTest {
    val formGateway = mock<FormGateway>()

    lateinit var saveFormUseCase: SaveFormUseCase

    @Before
    fun setUp() {
        saveFormUseCase = SaveFormUseCaseImpl(formGateway)
    }

    @Test
    fun execute() {
        val callback = mock<() -> Unit>()
        var captor = argumentCaptor<() -> Unit>()

        saveFormUseCase.execute("ANY_NAME", "ANY_PHONE", callback)

        verify(formGateway).persist(any(), captor.capture())
        captor.firstValue.invoke()
        verify(callback, times(1)).invoke()
    }
}
