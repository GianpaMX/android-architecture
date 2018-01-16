package io.github.gianpamx.android.architecture.data.room

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import io.github.gianpamx.android.architecture.entity.Form
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FormRepositoryTest {

    val formDao = mock<FormDao>()

    lateinit var formRepository: FormRepository

    @Before
    fun setUp() {
        formRepository = FormRepository(formDao)
    }

    @Test
    fun persistSync() {
        val success = mock<() -> Unit>()

        formRepository.persistSync(Form("ANY_NAME", "ANY_PHONE"), success)

        verify(success, times(1)).invoke()
    }
}
