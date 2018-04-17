package io.github.gianpamx.android.architecture.usecase

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import io.github.gianpamx.android.architecture.data.FormGateway
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SaveFormUseCaseImplTest {
    @Mock
    lateinit var formGateway: FormGateway

    lateinit var saveFormUseCaseImpl: SaveFormUseCaseImpl

    @Before
    fun setUp() {
        saveFormUseCaseImpl = SaveFormUseCaseImpl(formGateway)
    }

    @Test
    fun saveFormSuccessfully() {
        saveFormUseCaseImpl.executeSync("ANY_NAME", "ANY_PHONE")

        verify(formGateway).persist(any())
    }
}
