package com.assignment.meteoriteapp.ui.favourite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.assignment.meteoriteapp.data.Meteor
import com.assignment.meteoriteapp.ui.data.TestData
import com.assignment.meteoriteapp.ui.home.MeteorRepository
import com.assignment.meteoriteapp.ui.home.MeteorViewModel
import io.mockk.mockk
import io.mockk.spyk
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class FavoriteMeteorViewModelTest {

    private lateinit var viewModel: FavouriteMeteorViewModel
    private lateinit var meteoriewModel: MeteorViewModel
    private lateinit var repository: MeteorRepository

    @Before
    @Throws(Exception::class)
    fun setUpBefore() {
        repository = mockk<MeteorRepository>()
        meteoriewModel = MeteorViewModel(null, repository)
        viewModel = FavouriteMeteorViewModel(null, repository)
    }

    @After
    fun setUpAfter() {

    }

    @Test
    fun insertAndCheckList() {
//        viewModel.favouriteMeteorList.observeForever {
//            Log.i("TAG", "ABCD")
//        }
       /* val meteor = TestData.getTestData("test")
        meteoriewModel.insertFavoriteMeteors(meteor)
        viewModel.fetchMeteorsByPage()

        Mockito.`when`(viewModel.fetchMeteorsByPage())*/


        val mutableLiveData = MutableLiveData<Meteor>()
        val meteorData = TestData.getTestData("test")
        mutableLiveData.postValue(meteorData)

        assertEquals(meteorData.name, mutableLiveData.value?.name)

    }

    /* @Test
     @Throws(Exception::class)
     fun readWriteMeteorListContains() = runBlocking {
         try {
             val meteorInsert: Meteor = TestData.getTestData("abcd")
             meterDao.insertMeteor(meteorInsert)
             val meteorList = meterDao.getAllSavedMeteor()
             TestCase.assertTrue(meteorList.contains(meteorInsert))
         } catch (e: java.lang.Exception) {
             Log.i("MeteorViewModelUiTest", e.message!!)
         }
     }*/

    @Test
    fun onSaveButtonClickTest() {
//        val country = Country(1, "CN", "https://www.countryflags.io/cn/shiny/64.pn")
//        viewModel.insertCountry(country)
//        Mockito.verify(viewModel, Mockito.only()).getSavedCountries()
    }

    @Test
    fun isLiveDataEmitting_observeForever() {
//        val country = Country(1, "CN", "https://www.countryflags.io/cn/shiny/64.pn")
//        viewModel.insertCountry(country)
//
//        viewModel.observSavedCountries().observeForever { }
//
//        Assert.assertEquals(viewModel.getStateOfCountry().value?.data?.get(0)?.countryCode, "CN")
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
    fun isValidMeteorList() {
    }

    @Test
    fun setViewModel() {
    }

    @Test
    fun fetchMeteorsByPage() {
    }

    @Test
    fun getAllFevoriteMeteors() {
    }

    @Test
    fun removeFavoriteMeteors() {
    }

    private fun createMeteorObserver(): Observer<List<Meteor>> =
        spyk(Observer { })
}