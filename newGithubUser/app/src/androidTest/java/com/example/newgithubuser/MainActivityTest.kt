package com.example.newgithubuser

import android.view.View
import android.view.View.FIND_VIEWS_WITH_TEXT
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.newgithubuser.presentation.ui.MainActivity
import org.hamcrest.Matcher
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java, true, true)

    @Test
    fun loadingSpinngerShouldbeDisplayed() {
        //Thread.sleep(10000)
        onView(withId(R.id.loading)).check(matches(isDisplayed()))
        //onView(withId(R.id.loading)).check(matches(not(isDisplayed())))
    }

    @Test
    fun listViewsShouldBeDisplayed() {
        //onView(withId(R.id.profilePic)).check(matches(isDisplayed()))
        onView(withText("Joes Repo")).check(matches(isDisplayed()))
        onView(withText("Second Tester name")).check(matches(isDisplayed()))
        //onView(withText("2")).check(matches(isDisplayed()))
    }

    @Test
    fun listViewsShouldBeDisplayedBetterApproach() {
        onView(withId(R.id.repoList)).check(hasViewWithTextAtPosition(1, "Joes Repo"))
    }


    @Test
    fun listViewsShouldBeDisplayedEvenBetterApproach() {
        onView(
            withId(R.id.repoList)).check(hasTextViewWithIdHasTextAtPosition(
            1,
            "Joes Repo",
                R.id.repoName
            )
        )
    }
}

fun hasHolderItemAtPosition(
    index: Int,
    viewHolderMatcher: Matcher<ViewHolder?>?
): ViewAssertion? {
    return ViewAssertion { view, e ->
        if (view !is RecyclerView) {
            throw e!!
        }
        Assert.assertThat(view.findViewHolderForAdapterPosition(index), viewHolderMatcher)
    }
}

fun hasViewWithTextAtPosition(index: Int, text: CharSequence): ViewAssertion? {
    return ViewAssertion { view, e ->
        if (view !is RecyclerView) {
            throw e!!
        }
        val outviews: ArrayList<View> = ArrayList()
        view.findViewHolderForAdapterPosition(index)!!.itemView.findViewsWithText(
            outviews, text,
            FIND_VIEWS_WITH_TEXT
        )
        assert(outviews.isNotEmpty())
        /*
        import com.google.common.truth.Truth; Bu library'i eklemediğim içim aşağısnı commentledim
         */
        /*Truth.assert_()
            .withFailureMessage("There's no view at index $index of recyclerview that has text : $text")
            .that(outviews).isNotEmpty()*/
    }
}

fun hasTextViewWithIdHasTextAtPosition(
    index: Int,
    text: String,
    id: Int
): ViewAssertion? {
    return ViewAssertion { view, e ->
        if (view !is RecyclerView) {
            throw e!!
        }
        val textView = view.findViewHolderForAdapterPosition(index)!!.itemView.findViewById<TextView>(id)
        assert(textView.text.toString() == text)
    }
}
