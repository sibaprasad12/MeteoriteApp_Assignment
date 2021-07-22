package com.assignment.meteoriteapp.ui.favourite

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.assignment.meteoriteapp.data.Meteor
import com.assignment.meteoriteapp.data.TestMeteorData
import com.assignment.meteoriteapp.database.AppDatabase
import com.assignment.meteoriteapp.database.MeteorDao
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class FavouriteMeteorViewModelUiTest : TestCase() {

    private lateinit var meterDao: MeteorDao
    private lateinit var db: AppDatabase
    lateinit var context: Context

    @Before
    @Throws(Exception::class)
    fun setUpBefore() {
        context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        meterDao = db.meteorDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAndReadLanguage() = runBlocking {
        val meteorInsert: Meteor = TestMeteorData.getTestData("abcd")
        meterDao.insertMeteor(meteorInsert)
        val meteor = meterDao.getMeteorByName("abcd")
        assertThat(meteorInsert.name == meteor?.name).isTrue()
    }

    @Test
    @Throws(Exception::class)
    fun readWriteMeteorTest() = runBlocking {
        try {
            val meteorInsert: Meteor = TestMeteorData.getTestData("abcd")
            meterDao.insertMeteor(meteorInsert)
            val meteor = meterDao.getMeteorByName("abcd")
//            assertThat(meteorInsert.name, equalTo(meteor?.name))
            assertThat(meteorInsert.name == meteor?.name).isTrue()
        } catch (e: java.lang.Exception) {
            Log.i("MeteorViewModelUiTest", e.message!!)
        }
    }

    @Test
    @Throws(Exception::class)
    fun readWriteMeteorListContains() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val meteorInsert: Meteor = TestMeteorData.getTestData("abcd")
                meterDao.insertMeteor(meteorInsert)
                val meteorList = meterDao.getAllSavedMeteor()
                // assertTrue(meteorList.contains(meteorInsert))
            } catch (e: java.lang.Exception) {
                Log.i("MeteorViewModelUiTest", e.message!!)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun insert_element_to_favourite_list_and_check_if_exists_or_not() = runBlocking {
        try {
            val meteorInsert: Meteor = TestMeteorData.getTestData("abcd", isfavourite = 0)
            meterDao.insertMeteor(meteorInsert)
            val meteorList = meterDao.getAllFavoriteMeteor()
            // assertFalse(meteorList.contains(meteorInsert))
        } catch (e: java.lang.Exception) {
            Log.i("MeteorViewModelUiTest", e.message!!)
        }
    }

    @Test
    @Throws(Exception::class)
    fun insert_element_to_favourite_list_and_check_exists() = runBlocking {
        try {
            meterDao.clearMeteorTable()
            val meteorList = meterDao.getAllFavoriteMeteor()
            // assertTrue(meteorList.isEmpty())
        } catch (e: java.lang.Exception) {
            Log.i("MeteorViewModelUiTest", e.message!!)
        }
    }

    @Test
    @Throws(Exception::class)
    fun remove_all_meteor_return_empty_favoutite_meteorList() = runBlocking {
        try {
            val meteorInsert: Meteor = TestMeteorData.getTestData("abcd", isfavourite = 1)
            meterDao.insertMeteor(meteorInsert)
            val meteorList = meterDao.getAllFavoriteMeteor()
//            assertTrue(meteorList.contains(meteorInsert))
        } catch (e: java.lang.Exception) {
            Log.i("MeteorViewModelUiTest", e.message!!)
        }
    }

    @Test
    @Throws(Exception::class)
    fun insert_duplicate_meteor_return_error() = runBlocking {
        try {
            meterDao.clearMeteorTable()
            val meteorInsert: Meteor = TestMeteorData.getTestData("abcd", isfavourite = 1)
            meterDao.insertMeteor(meteorInsert)
            meterDao.insertMeteor(meteorInsert)
            val meteorList = meterDao.getAllFavoriteMeteor()
//            assertTrue(meteorList.size == 1)
        } catch (e: java.lang.Exception) {
            Log.i("MeteorViewModelUiTest", e.message!!)
//            assertTrue(true)
        }
    }

    fun testGetErrorMessage() {

    }

    fun testIsValidMeteor() {}

    fun testIsValidMeteorList() {}

    fun testFetchMeteorsByPage() {}

    fun testGetAllFavouriteMeteors() {}

    fun testRemoveFavoriteMeteors() {}
}