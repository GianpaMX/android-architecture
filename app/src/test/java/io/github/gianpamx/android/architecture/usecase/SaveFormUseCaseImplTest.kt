package io.github.gianpamx.android.architecture.usecase

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test

class SaveFormUseCaseImplTest {
    lateinit var saveFormUseCase: SaveFormUseCase

    @Before
    fun setUp() {
        saveFormUseCase = SaveFormUseCaseImpl()
    }

    @Test
    fun execute() {
        val callback = mock<() -> Unit>()

        saveFormUseCase.execute("ANY_NAME", "ANY_PHONE", callback)

        verify(callback, times(1)).invoke()
    }
}
