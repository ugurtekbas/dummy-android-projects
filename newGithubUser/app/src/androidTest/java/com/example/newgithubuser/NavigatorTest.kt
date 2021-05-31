package com.example.newgithubuser

import android.app.Activity.RESULT_OK
import android.app.Instrumentation.ActivityResult
import android.app.Instrumentation
import android.content.Intent
import android.net.Uri
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.platform.app.InstrumentationRegistry
import com.example.newgithubuser.navigation.RepoNavigator
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.rule.IntentsTestRule
import com.example.newgithubuser.presentation.ui.MainActivity
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class NavigatorTest {

    @get:Rule
    val intentsTestRule = IntentsTestRule(MainActivity::class.java)

    private val context = InstrumentationRegistry.getInstrumentation().context
    private val navigator = RepoNavigator(context)
    private val url = "https://www.google.com"

    @Test
    fun navigatingWithCorrectParams() {
        val expectedIntent: Matcher<Intent> = allOf(
            hasAction(Intent.ACTION_VIEW),
            hasData(Uri.parse(url))
        )

        //val activityResult = createOpenBrowserActivityStub()
        //intending(expectedIntent).respondWith(activityResult)

        navigator.navigateToSite(url)
        intended(expectedIntent)
    }

    private fun createOpenBrowserActivityStub(): ActivityResult {
        val resultIntent = Intent().apply {
            data = Uri.parse("hey")
        }

        return ActivityResult(RESULT_OK, resultIntent)
    }
}
