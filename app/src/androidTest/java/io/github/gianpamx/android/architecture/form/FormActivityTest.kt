package io.github.gianpamx.android.architecture.form


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import io.github.gianpamx.androidarchitecure.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FormActivityTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(FormActivity::class.java)

    @Test
    fun formActivityTest() {
        onView(withId(R.id.nameEditText)).perform(replaceText("Juan"))
        onView(withId(R.id.phoneEditText)).perform(replaceText("5518911661"))
        onView(withId(R.id.sendButton)).perform(click())
    }
}
