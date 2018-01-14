package io.github.gianpamx.android.architecture.form

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.github.gianpamx.android.architecture.providers.DateTimeProvider
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*


@RunWith(JUnit4::class)
class FormViewModelTest {
    companion object {
        val FIRST_DATE = Date()
        val SECOND_DATE = Date(FIRST_DATE.time + 1000)
    }

    @Rule
    @JvmField
    var instantExecutor = InstantTaskExecutorRule()

    var dateTimeProvider = mock<DateTimeProvider>()

    var captor = argumentCaptor<DateTimeProvider.Listener>()


    lateinit var formViewModel: FormViewModel

    @Before
    fun setUp() {
        formViewModel = FormViewModel(dateTimeProvider)
    }

    @Test
    fun ticker() {
        verify(dateTimeProvider).start(captor.capture())

        captor.firstValue.onTick(FIRST_DATE)
        assertEquals(FIRST_DATE, formViewModel.dateTime.value);

        captor.firstValue.onTick(SECOND_DATE)
        assertEquals(SECOND_DATE, formViewModel.dateTime.value);
    }
}
