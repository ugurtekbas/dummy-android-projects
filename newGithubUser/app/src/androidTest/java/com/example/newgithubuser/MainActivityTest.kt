package com.example.newgithubuser

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.newgithubuser.presentation.ui.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java, true, true)

    /*private fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }*/

    @Before
    fun setUp() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        //val app = instrumentation.targetContext.applicationContext as TestNewGithubUserApplication
        //TestAppComponent.inject(this)
        /*Dagger.factory().create(app)

        app.appComponent = TestAppComponent.cre
        val testComponent = DaggerTestAppComponent.builder()
            .fakeApplicationModule(FakeApplicationModule(mockUserRepository))
            .build()
        app.component = testComponent*/

    }


    @Test
    fun abc() {
        onView(withId(R.id.loading)).check(matches(isDisplayed()))
    }

}
