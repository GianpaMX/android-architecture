package io.github.gianpamx.android.architecture.usecase

import com.nhaarman.mockito_kotlin.whenever
import io.github.gianpamx.android.architecture.data.FormGateway
import io.github.gianpamx.android.architecture.entity.Form
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetFormUseCaseImplTest {
    @Mock
    lateinit var formGateway: FormGateway

    lateinit var getFormUseCaseImpl: GetFormUseCaseImpl

    @Before
    fun setUp() {
        getFormUseCaseImpl = GetFormUseCaseImpl(formGateway)
    }

    @Test
    fun findFormSuccessfully() {
        val expectedForm = Form()
        whenever(formGateway.findForm()).thenReturn(expectedForm)

        val form = getFormUseCaseImpl.executeSync()

        assertEquals(expectedForm, form)
    }

    @Test
    fun formNotFound() {
        whenever(formGateway.findForm()).thenReturn(null)

        val form = getFormUseCaseImpl.executeSync()

        assertNull(form)
    }
}
