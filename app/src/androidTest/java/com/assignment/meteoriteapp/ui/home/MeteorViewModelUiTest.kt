package com.assignment.meteoriteapp.ui.home

import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.assignment.meteoriteapp.data.Meteor
import com.assignment.meteoriteapp.data.TestMeteorData
import com.assignment.meteoriteapp.database.AppDatabase
import com.assignment.meteoriteapp.database.DatabaseClient
import com.assignment.meteoriteapp.database.MeteorDao
import com.assignment.meteoriteapp.network.ApiHelper
import com.assignment.meteoriteapp.network.RetrofitBuilder
import junit.framework.TestCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
internal class MeteorViewModelUiTest : TestCase() {

    private lateinit var meterDao: MeteorDao
    private lateinit var db: AppDatabase

    lateinit var context: Context

    private lateinit var viewModel: MeteorViewModel
    private lateinit var repository: MeteorRepository

    @Before
    @Throws(Exception::class)
    fun setUpBefore() {
        context = ApplicationProvider.getApplicationContext<Context>()
        db = DatabaseClient.getInstance(context)?.getAppDatabase()!!
        meterDao = db.meteorDao()
//        repository = MeteorRepository(ApiHelper(RetrofitBuilder.apiService))
//        viewModel = MeteorViewModel(context, repository)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

   /* @Test
    fun readWriteMeteorTest() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val meteorInsert: Meteor = TestMeteorData.getTestData("abcd")
                meterDao.insertMeteor(meteorInsert)
                val meteor = meterDao.getMeteorByName("abcd")
                assertThat(meteorInsert.name, equalTo(meteor?.name))
            } catch (e: java.lang.Exception) {
//                Log.i("MeteorViewModelUiTest", e.message!!)
            }
        }
    }

    @Test
    fun readWriteMeteorListCOntains() = runBlocking {
        try {
            val meteorInsert: Meteor = TestMeteorData.getTestData("abcd")
            meterDao.insertMeteor(meteorInsert)
            val meteorList = meterDao.getAllSavedMeteor()
            assertTrue(meteorList.contains(meteorInsert))
        } catch (e: java.lang.Exception) {
            Log.i("MeteorViewModelUiTest", e.message!!)
        }
    }

    @Test
    @Throws(Exception::class)
    fun insert_element_to_favourite_list_and_check_if_exists_or_not() = runBlocking {
        try {
            val meteorInsert: Meteor = TestMeteorData.getTestData("abcd", isfavourite = 0)
            meterDao.insertMeteor(meteorInsert)
            val meteorList = meterDao.getAllFavoriteMeteor()
            assertFalse(meteorList.contains(meteorInsert))
        } catch (e: java.lang.Exception) {
            Log.i("MeteorViewModelUiTest", e.message!!)
        }
    }

    @Test
    fun insert_element_to_favourite_list_and_check_exists() = runBlocking {
        try {
            val meteorInsert: Meteor = TestMeteorData.getTestData("abcd", isfavourite = 1)
            meterDao.insertMeteor(meteorInsert)
            val meteorList = meterDao.getAllFavoriteMeteor()
            assertTrue(meteorList.contains(meteorInsert))
        } catch (e: java.lang.Exception) {
            Log.i("MeteorViewModelUiTest", e.message!!)
        }
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
    fun getMeteors() {
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
    }*/
}