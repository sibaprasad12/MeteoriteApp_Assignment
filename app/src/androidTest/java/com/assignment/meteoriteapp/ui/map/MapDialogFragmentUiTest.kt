package com.assignment.meteoriteapp.ui.map

import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.assignment.meteoriteapp.R
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Sibaprasad Mohanty on 17/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

@RunWith(AndroidJUnit4::class)
class MapDialogFragmentUiTest {

    @Test
    fun launchDialogFragmentAndVerifyUI() {
        // Use launchFragment to launch the dialog fragment in a dialog.
        val scenario = launchFragment<MeteorMapDialogFragment>()

        scenario.onFragment { fragment ->
            assertThat(fragment.dialog).isNotNull()
            assertThat(fragment.requireDialog().isShowing).isTrue()
        }

        // Now use espresso to look for the fragment's text view and verify it is displayed.
        Espresso.onView(ViewMatchers.withId(R.id.tvTitle)).inRoot(isDialog())
            .check(ViewAssertions.matches(ViewMatchers.withText("I am a fragment")));
    }

    @Test
    fun launchDialogFragmentEmbeddedToHostActivityAndVerifyUI() {
        // Use launchFragmentInContainer to inflate a dialog fragment's view into Activity's content view.
        val scenario = launchFragmentInContainer<MeteorMapDialogFragment>()

        scenario.onFragment { fragment ->
            // Dialog is not created because you use launchFragmentInContainer and the view is inflated
            // into the Activity's content view.
            assertThat(fragment.dialog).isNull()
        }

        // Now use espresso to look for the fragment's text view and verify it is displayed.
        Espresso.onView(ViewMatchers.withId(R.id.tvTitle))
            .check(ViewAssertions.matches(ViewMatchers.withText("I am a fragment")));
    }
}

