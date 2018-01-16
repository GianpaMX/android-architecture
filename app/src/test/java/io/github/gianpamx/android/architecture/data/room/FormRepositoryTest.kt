package io.github.gianpamx.android.architecture.data.room

import com.nhaarman.mockito_kotlin.*
import io.github.gianpamx.android.architecture.entity.Form
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.internal.verification.NoMoreInteractions

@RunWith(JUnit4::class)
class FormRepositoryTest {

    companion object {
        val ANY_NAME = "ANY_NAME"
        val ANY_PHONE = "ANY_PHONE"
    }

    val formDao = mock<FormDao>()

    lateinit var formRepository: FormRepository

    @Before
    fun setUp() {
        formRepository = FormRepository(formDao)
    }

    @Test
    fun persistSync() {
        val success = mock<() -> Unit>()

        formRepository.persistSync(Form(ANY_NAME, ANY_PHONE), success)

        verify(success, times(1)).invoke()
    }

    @Test
    fun findNotExistingForm() {
        val success = mock<(form: Form) -> Unit>()
        whenever(formDao.findForm()).thenReturn(null)

        formRepository.findFormSync(success)

        verify(success, NoMoreInteractions()).invoke(any())
    }

    @Test
    fun findExistingForm() {
        val success = mock<(form: Form) -> Unit>()
        whenever(formDao.findForm()).thenReturn(FormRoom(ANY_NAME, ANY_PHONE))

        formRepository.findFormSync(success)

        verify(success, times(1)).invoke(any())
    }
}
