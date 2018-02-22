package io.github.gianpamx.android.architecture.form


import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argumentCaptor
import io.github.gianpamx.android.architecture.app.TestApp
import io.github.gianpamx.android.architecture.data.FormGateway
import io.github.gianpamx.android.architecture.entity.Form
import io.github.gianpamx.androidarchitecture.R
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.verify
import javax.inject.Inject


@RunWith(AndroidJUnit4::class)
class FormActivityTest {

    companion object {
        val ANY_NAME = "Gianpa"
        val ANY_PHONE = "5512341234"
    }

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(FormActivity::class.java)

    @Inject
    lateinit var formGateway: FormGateway

    @Before
    fun setUp() {
        val testApp = activityTestRule.activity.applicationContext as TestApp
        testApp.testAppComponent.inject(this)
    }


    @After
    fun tearDown() {
        Mockito.reset(formGateway)
    }

    @Test
    fun sendForm() {
        val captor = argumentCaptor<() -> Unit>()

        onView(withId(R.id.nameEditText)).perform(scrollTo()).perform(replaceText(ANY_NAME))
        onView(withId(R.id.phoneEditText)).perform(scrollTo()).perform(replaceText(ANY_PHONE))
        onView(withId(R.id.sendButton)).perform(scrollTo()).perform(click())

        verify(formGateway).persist(any(), captor.capture())
        captor.firstValue.invoke()

        assertTrue(activityTestRule.activity.isFinishing)
    }

    @Test
    fun existingFormData() {
        val captor = argumentCaptor<(Form) -> Unit>()

        verify(formGateway).findForm(captor.capture())
        captor.firstValue.invoke(Form(ANY_NAME, ANY_PHONE))
        assertTrue(activityTestRule.activity.isFinishing)
    }

    @Test
    fun noExistingFormData() {
        assertFalse(activityTestRule.activity.isFinishing)
    }
}
