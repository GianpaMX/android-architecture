package io.github.gianpamx.android.architecture.form

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.github.gianpamx.android.architecture.entity.EmptyNameExeption
import io.github.gianpamx.android.architecture.entity.EmptyPhoneExeption
import io.github.gianpamx.android.architecture.entity.Form
import io.github.gianpamx.android.architecture.providers.DateTimeProvider
import io.github.gianpamx.android.architecture.providers.VersionProvider
import io.github.gianpamx.android.architecture.usecase.GetFormUseCase
import io.github.gianpamx.android.architecture.usecase.SaveFormUseCase
import junit.framework.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import java.util.*


@RunWith(JUnit4::class)
class FormViewModelTest {
    companion object {
        val FIRST_DATE = Date()
        val SECOND_DATE = Date(FIRST_DATE.time + 1000)
        val ANY_NAME = "ANY_NAME"
        val ANY_PHONE = "ANY_PHONE"
    }

    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()

    val dateTimeProvider = mock<DateTimeProvider>()
    val saveFormUseCase = mock<SaveFormUseCase>()
    val getFormUseCase = mock<GetFormUseCase>()
    val versionProvider = mock<VersionProvider>()

    lateinit var formViewModel: FormViewModel

    @Before
    fun setUp() {
        formViewModel = FormViewModel(dateTimeProvider, saveFormUseCase, getFormUseCase, versionProvider)
    }

    @Test
    fun ticker() {
        val captor = argumentCaptor<DateTimeProvider.Listener>()
        verify(dateTimeProvider).start(captor.capture())

        captor.firstValue.onTick(FIRST_DATE)
        assertEquals(FIRST_DATE, formViewModel.dateTime.value);

        captor.firstValue.onTick(SECOND_DATE)
        assertEquals(SECOND_DATE, formViewModel.dateTime.value);
    }

    @Test
    fun sendFailure() {
        val captor = argumentCaptor<(Throwable) -> Unit>()

        formViewModel.send("ANY_STRING", "ANY_STRING")

        verify(saveFormUseCase).execute(anyString(), anyString(), any(), captor.capture())
        captor.firstValue.invoke(Exception())
        assertTrue(formViewModel.error.value is Exception)
    }

    @Test
    fun sendSuccessfully() {
        val captor = argumentCaptor<() -> Unit>()

        formViewModel.send(ANY_NAME, ANY_PHONE)

        verify(saveFormUseCase).execute(anyString(), anyString(), captor.capture(), any())
        captor.firstValue.invoke()

        assertTrue(formViewModel.isFormSaved.value!!)
    }

    @Test
    fun existingFormData() {
        var captor = argumentCaptor<(Form) -> Unit>()

        verify(getFormUseCase).execute(captor.capture())
        captor.firstValue.invoke(Form(ANY_NAME, ANY_PHONE))

        assertTrue(formViewModel.isFormSaved.value!!)
    }

    @Test
    fun formNotSaved() {
        verify(getFormUseCase).execute(any())

        assertFalse(formViewModel.isFormSaved.value!!)
    }
}
