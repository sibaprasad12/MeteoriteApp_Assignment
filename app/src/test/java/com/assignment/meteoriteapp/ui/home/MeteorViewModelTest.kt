package com.assignment.meteoriteapp.ui.home

import android.content.Context
import com.assignment.meteoriteapp.data.Meteor
import com.assignment.meteoriteapp.network.ApiHelper
import com.assignment.meteoriteapp.network.RetrofitBuilder
import com.assignment.meteoriteapp.ui.data.TestData
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock


@RunWith(JUnit4::class)
internal class MeteorViewModelTest {

    @Mock
    lateinit var meteorFragment: MeteorFragment

    @Mock
    lateinit var meteorViewModel: MeteorViewModel

    @Mock
    lateinit var context: Context

    private lateinit var viewModel: MeteorViewModel
    private lateinit var repository: MeteorRepository

    @Before
    @Throws(Exception::class)
    fun setUpBefore() {
//        context = InstrumentationRegistry.getInstrumentation().context
//        repository = MeteorRepository()
//        viewModel = MeteorViewModel(repository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getObsevableLoading() {
    }

    @Test
    fun setObsevableLoading() {
    }

    @Test
    fun getLoadMore() {
    }

    @Test
    fun setLoadMore() {
    }

    @Test
    fun getErrorMessage() {
    }

    @Test
    fun setErrorMessage() {
    }

    @Test
    fun isValidMeteor() {
    }

    @Test
    fun checkEMptyMeteorList() {
        val list = emptyList<Meteor>()
        assertFalse(viewModel.isValidMeteorList(list))
    }

    @Test
    fun checkMeteorListWithData() {
        val list = TestData.getMeteorList()
        assertTrue(viewModel.isValidMeteorList(list))
    }

    @Test
    fun getMeteors() = runBlocking {
        viewModel.fetchMeteorsByPage()
        delay(5000)
        assertTrue(viewModel.isValidMeteorList())
    }

    @Test
    fun getAllFevoriteMeteors() {
    }

    @Test
    fun filterMeteors() {
    }

    @Test
    fun fetchMeteorsByPage() {

    }

    @Test
    fun insertFavoriteMeteors() {

    }
}