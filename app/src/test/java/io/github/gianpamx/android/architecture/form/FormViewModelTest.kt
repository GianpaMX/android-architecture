package io.github.gianpamx.android.architecture.form

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.isNull
import com.nhaarman.mockito_kotlin.verify
import io.github.gianpamx.android.architecture.entity.Form
import io.github.gianpamx.android.architecture.providers.DateTimeProvider
import io.github.gianpamx.android.architecture.providers.VersionProvider
import io.github.gianpamx.android.architecture.usecase.GetFormUseCase
import io.github.gianpamx.android.architecture.usecase.SaveFormUseCase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*


private val FIRST_DATE = Date()
private val SECOND_DATE = Date(FIRST_DATE.time + 1000)

private const val ANY_NAME = "ANY_NAME"
private const val ANY_PHONE = "ANY_PHONE"

@RunWith(MockitoJUnitRunner::class)
class FormViewModelTest {
    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()

    @Mock
    lateinit var dateTimeProvider: DateTimeProvider

    @Mock
    lateinit var saveFormUseCase: SaveFormUseCase

    @Mock
    lateinit var getFormUseCase: GetFormUseCase

    @Mock
    lateinit var versionProvider: VersionProvider

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
        val captor = argumentCaptor<(Form) -> Unit>()

        verify(getFormUseCase).execute(captor.capture(), isNull())
        captor.firstValue.invoke(Form(ANY_NAME, ANY_PHONE))

        assertTrue(formViewModel.isFormSaved.value!!)
    }

    @Test
    fun formNotSaved() {
        verify(getFormUseCase).execute(any(), isNull())

        assertFalse(formViewModel.isFormSaved.value!!)
    }
}
