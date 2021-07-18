package com.assignment.meteoriteapp.ui.home

import com.assignment.meteoriteapp.testData.Meteor
import com.assignment.meteoriteapp.ui.data.TestData
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
internal class MeteorViewModelTest {

    private lateinit var meteorViewModel: MeteorViewModel
    private lateinit var repository: MeteorRepository

    @Before
    @Throws(Exception::class)
    fun setUpBefore() {
        repository = mockk<MeteorRepository>()
        meteorViewModel = MeteorViewModel(null, repository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun filterListByName() {
        meteorViewModel.meteorsListByPage = TestData.getMeteorList()
        val meteorData = TestData.getTestData("aaaa", "103", "2003-01-03", 1)
        meteorViewModel.meteorsListByPage.add(meteorData)
        val list = meteorViewModel.filterMeteors(MeteorViewModel.FILTER_BY_NAME)
        assertTrue(list[0].name == meteorData.name)
    }

    @Test
    fun filterListByMassAscending() {
        meteorViewModel.meteorsListByPage = TestData.getMeteorList()
        val meteorData =
            TestData.getTestData("Light Meteor at first position", "1", "2003-01-03", 1)
        meteorViewModel.meteorsListByPage.add(meteorData)
        val list = meteorViewModel.filterMeteors(MeteorViewModel.FILTER_BY_NAME)
        assertTrue(list[0].mass == meteorData.mass)
    }

    @Test
    fun filterListByMassDescending() {
        meteorViewModel.meteorsListByPage = TestData.getMeteorList()
        val meteorData =
            TestData.getTestData("Heavy Meteor at first position", "1003", "2003-01-03", 1)
        meteorViewModel.meteorsListByPage.add(meteorData)
        val list = meteorViewModel.filterMeteors(MeteorViewModel.FILTER_BY_NAME)
        assertTrue(list[0].mass == meteorData.mass)
    }

    @Test
    fun filterListByDateAscending() {
        meteorViewModel.meteorsListByPage = TestData.getMeteorList()
        val meteorData =
            TestData.getTestData("Oldest Meteor at first position", "1", "1901-01-03", 1)
        meteorViewModel.meteorsListByPage.add(meteorData)
        val list = meteorViewModel.filterMeteors(MeteorViewModel.FILTER_BY_NAME)
        assertTrue(list[0].getDateTime() == meteorData.getDateTime())
    }

    @Test
    fun filterListByDateDescending() {
        meteorViewModel.meteorsListByPage = TestData.getMeteorList()
        val meteorData =
            TestData.getTestData("Latest Meteor at first position", "1", "2021-01-03", 1)
        meteorViewModel.meteorsListByPage.add(meteorData)
        val list = meteorViewModel.filterMeteors(MeteorViewModel.FILTER_BY_NAME)
        assertTrue(list[0].getDateTime() == meteorData.getDateTime())
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
        assertFalse(meteorViewModel.isValidMeteorList(list))
    }

    @Test
    fun checkMeteorListWithData() {
        val list = TestData.getMeteorList()
        assertTrue(meteorViewModel.isValidMeteorList(list))
    }

    @Test
    fun getMeteors() = runBlocking {
        meteorViewModel.fetchMeteorsByPage()
        delay(5000)
        assertTrue(meteorViewModel.isValidMeteorList())
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