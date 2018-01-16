package io.github.gianpamx.android.architecture.form


import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argumentCaptor
import io.github.gianpamx.android.architecture.entity.Form
import io.github.gianpamx.androidarchitecture.R
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.anyString
import org.mockito.Mockito.verify


@RunWith(AndroidJUnit4::class)
class FormActivityTest {

    companion object {
        val ANY_NAME = "Juan"
        val ANY_PHONE = "5518911661"
    }

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(FormActivity::class.java)

    @Test
    fun sendForm() {
        val captor = argumentCaptor<() -> Unit>()

        onView(withId(R.id.nameEditText)).perform(replaceText(ANY_NAME))
        onView(withId(R.id.phoneEditText)).perform(replaceText(ANY_PHONE))
        onView(withId(R.id.sendButton)).perform(click())

        verify(activityTestRule.activity.formViewModel.saveFormUseCase).execute(anyString(), anyString(), captor.capture())
        captor.firstValue.invoke()

        assertTrue(activityTestRule.getActivity().isFinishing())
    }

    @Test
    fun existingFormData() {
        val captor = argumentCaptor<(form: Form) -> Unit>()

        verify(activityTestRule.activity.formViewModel.getFormUseCase).execute(captor.capture(), any())
        captor.firstValue.invoke(Form(ANY_NAME, ANY_PHONE))

        assertTrue(activityTestRule.getActivity().isFinishing())
    }
}
