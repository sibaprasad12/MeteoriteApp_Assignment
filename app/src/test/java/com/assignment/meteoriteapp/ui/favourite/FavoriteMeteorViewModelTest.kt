package com.assignment.meteoriteapp.ui.favourite

import android.content.Context
import android.util.Log
import com.assignment.meteoriteapp.data.Meteor
import com.assignment.meteoriteapp.database.AppDatabase
import com.assignment.meteoriteapp.database.DatabaseClient
import com.assignment.meteoriteapp.database.MeteorDao
import com.assignment.meteoriteapp.ui.data.TestData
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class FavoriteMeteorViewModelTest {

    @Mock
    private lateinit var mockApplicationContext: Context

    private lateinit var meterDao: MeteorDao
    private lateinit var db: AppDatabase

    // private lateinit var viewModel: FavouriteMeteorViewModel

    @Before
    @Throws(Exception::class)
    fun setUpBefore() {
        db = DatabaseClient.getInstance(mockApplicationContext)?.getAppDatabase()!!
        meterDao = db.meteorDao()
    }

    @After
    fun setUpAfter() {

    }

    @Test
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
    }


    @Test
    fun countryCodeValidator_correctContryCode_returnTrue() {
//        Assert.assertTrue(viewModel.isValidMeteor(Meteor()))
//        Assert.assertTrue(viewModel.isValidCountry("gh"))
//        Assert.assertTrue(viewModel.isValidCountry("in"))
    }

    @Test
    fun validMeteorList() {
//        val countryList = emptyList<Meteor>()
//        Assert.assertFalse(viewModel.isValidMeteorList(countryList))
//        val meteor = TestData.getTestData("abcd")
//        val nonEMptyCountryList = ArrayList<Meteor>()
//        nonEMptyCountryList.add(meteor)
//        Assert.assertTrue(viewModel.isValidMeteorList(nonEMptyCountryList))
    }

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


}