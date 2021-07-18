package com.assignment.meteoriteapp.ui.home

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.assignment.meteoriteapp.R
import com.assignment.meteoriteapp.ui.MeteorActivity
import com.assignment.meteoriteapp.ui.adapter.MeteorAdapter
import com.assignment.meteoriteapp.utils.NetworkUtil
import org.hamcrest.CoreMatchers
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito


/**
 * Created by Sibaprasad Mohanty on 17/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */


@RunWith(JUnit4::class)
class MeteorFragmentUiTest {

    @Rule
    @JvmField
    val rule = getRule()

    @Mock
    var weatherViewModel: MeteorViewModel = Mockito.mock(MeteorViewModel::class.java)

    private lateinit var instrumentationCtx: Context

    private fun getRule(): ActivityTestRule<MeteorActivity> {
        Log.e("Initalising rule", "getting Mainactivity")
        return ActivityTestRule(MeteorActivity::class.java)
    }

    @BeforeClass
    fun before_class_method() {
        Log.e("@Before Class", "Hi this is run before anything")
        rule.activity.setUpNavigation()
        instrumentationCtx = InstrumentationRegistry.getInstrumentation().context
    }


    @AfterClass
    fun after_class_method() {
        Log.e("@After Class", "Hi this is run after everything")
    }

    @Before
    fun before_test_method() {
        Log.e("@Before", "Hi this is run before every test function")
    }


    @Test
    fun meteorFetch_success() {

    }

    @Test
    fun meteorFetch_failure() {

    }

    @Test
    fun testInternetConnection() {
        object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(message: Message) {
                if (NetworkUtil.isAvailable(instrumentationCtx as AppCompatActivity)) {
                    Toast.makeText(instrumentationCtx, "Internet Available", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(instrumentationCtx, "No Internet Available", Toast.LENGTH_SHORT)
                        .show()
                }
                Handler().postDelayed(Runnable { }, 5000)
            }
        }
    }

    @After
    fun after_test_method() {
        Log.e("@After", "Hi this is run after every test function")
    }

    @Test
    fun meteorList_CheckValueAtPosition() {
        /*Espresso.onView(ViewMatchers.withId(R.id.recyclerViewMeteor))
            .perform(RecyclerViewActions.scrollTo<MeteorAdapter.MeteorViewHolder>(withId(R.id.constraintItem)))
        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.withId(R.id.textViewWeatherType),
                ViewMatchers.withText("Flag")
            )
        )*/
    }

    @Test
    @Throws(InterruptedException::class)
    fun testGetMeteorList() {

       /* meteorViewModel.fetchMeteorsByPage(instrumentationCtx, "London")

        assertTrue(!rule.activity.isFinishing)

        onData(withId(R.id.cardRootItem))
            .inAdapterView(withId(R.id.constraintItem))
            .atPosition(0)
            .perform(click())*/
    }

}