package io.github.gianpamx.android.architecture.form


import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.whenever
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo
import io.github.gianpamx.android.architecture.app.TestApp
import io.github.gianpamx.android.architecture.data.room.FormDao
import io.github.gianpamx.android.architecture.data.room.FormRoom
import io.github.gianpamx.androidarchitecture.R
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.reset
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
    var activityTestRule = ActivityTestRule(FormActivity::class.java, false, false)

    @Inject
    lateinit var formDao: FormDao

    @Before
    fun setUp() {
        val testApp = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TestApp
        testApp.testAppComponent.inject(this)
        reset(formDao)
    }

    @Test
    fun sendForm() {
        activityTestRule.launchActivity(Intent())
        writeTo(R.id.nameEditText, ANY_NAME)
        writeTo(R.id.phoneEditText, ANY_PHONE)
        clickOn(R.id.sendButton)

        assertTrue(activityTestRule.activity.isFinishing)
    }

    @Test
    fun existingFormData() {
        whenever(formDao.findForm()).thenReturn(FormRoom(ANY_NAME, ANY_PHONE))

        activityTestRule.launchActivity(Intent())

        assertTrue(activityTestRule.activity.isFinishing)
    }

    @Test
    fun noExistingFormData() {
        whenever(formDao.findForm()).thenReturn(null)

        activityTestRule.launchActivity(Intent())

        assertFalse(activityTestRule.activity.isFinishing)
    }
}
