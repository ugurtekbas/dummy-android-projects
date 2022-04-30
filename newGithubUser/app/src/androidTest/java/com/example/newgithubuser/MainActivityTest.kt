package com.example.newgithubuser

import android.view.View
import android.view.View.FIND_VIEWS_WITH_TEXT
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.newgithubuser.presentation.ui.ListViewModel
import com.example.newgithubuser.presentation.ui.MainActivity
import com.example.newgithubuser.presentation.ui.ViewState
import org.hamcrest.Matcher
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import javax.inject.Inject
//import org.mockito.kotlin.any
//import org.mockito.kotlin.whenever

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    /*@Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory*/

    //var viewModel:ListViewModel = mock()
    val a = ViewState(true)
    private val stateLiveData: MutableLiveData<ViewState> = MutableLiveData(a)

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java, true, true)

    /*
    @Test
    fun loadingSpinngerShouldbeDisplayed() {
        activityRule.activity.viewModelFactory = createViewModelFor(viewModel)
        stateLiveData.value = ViewState(true)
        whenever(viewModel.viewState).thenReturn(stateLiveData)
        activityRule.launchActivity(null)

        onView(withId(R.id.loading)).check(matches(isDisplayed()))
        //onView(withId(R.id.loading)).check(matches(not(isDisplayed())))
    }*/

    @Test
    fun bbbbb() {
        /*`when`(viewModelFactory.create(ListViewModel::class.java).viewState).thenReturn(
            stateLiveData
        )

        onView(withId(R.id.loading)).check(matches(isDisplayed()))*/
    }

    @Test
    fun listViewsShouldBeDisplayed() {
        Thread.sleep(5000)
        onView(withText("Joe Pelo")).check(matches(isDisplayed()))
        //onView(withText("Second Tester name")).check(matches(isDisplayed()))
        //onView(withText("2")).check(matches(isDisplayed()))
    }

    @Test
    fun listViewsShouldBeDisplayedBetterApproach() {
        onView(withId(R.id.repoList)).check(hasViewWithTextAtPosition(1, "Joes Repo"))
    }

    @Test
    fun listViewsShouldBeDisplayedEvenBetterApproach() {
        Thread.sleep(10000)
        onView(
            withId(R.id.repoList)).check(hasTextViewWithIdHasTextAtPosition(
            1,
            "Joe Pelo",
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

fun <T : ViewModel> createViewModelFor(model: T): ViewModelProvider.Factory =
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(model.javaClass)) {
                return model as T
            }
            throw IllegalArgumentException("unexpected model class " + modelClass)
        }
    }
