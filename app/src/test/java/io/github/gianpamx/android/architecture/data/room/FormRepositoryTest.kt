package io.github.gianpamx.android.architecture.data.room

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.github.gianpamx.android.architecture.entity.Form
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

private const val ANY_NAME = "ANY_NAME"
private const val ANY_PHONE = "ANY_PHONE"

@RunWith(MockitoJUnitRunner::class)
class FormRepositoryTest {

    @Mock
    lateinit var formDao: FormDao

    lateinit var formRepository: FormRepository

    @Before
    fun setUp() {
        formRepository = FormRepository(formDao)
    }

    @Test
    fun persistSync() {
        formRepository.persist(Form(ANY_NAME, ANY_PHONE))

        verify(formDao).insert(any())
    }

    @Test
    fun findNotExistingForm() {
        whenever(formDao.findForm()).thenReturn(null)

        val form = formRepository.findForm()

        assertNull(form)
    }

    @Test
    fun findExistingForm() {
        val expectedForm = FormRoom(ANY_NAME, ANY_PHONE)
        whenever(formDao.findForm()).thenReturn(expectedForm)

        val form = formRepository.findForm()

        assertEquals(expectedForm.toForm(), form)
    }
}
