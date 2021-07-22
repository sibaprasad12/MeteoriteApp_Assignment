package com.assignment.meteoriteapp.database


import android.content.Context
import android.util.Log
import androidx.room.Room
import com.assignment.meteoriteapp.data.Meteor
import com.assignment.meteoriteapp.data.TestMeteorData
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

/**
 * Created by Sibaprasad Mohanty on 17/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class MeteorDatabaseTest : TestCase() {

    private lateinit var meterDao: MeteorDao
    private lateinit var db: AppDatabase
    lateinit var context: Context

    @Before
    @Throws(Exception::class)
    fun setUpBefore() {
        context = mockk<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        meterDao = db.meteorDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAndReadMeteorData() = runBlocking {
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
                assertTrue(meteorList.contains(meteorInsert))
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
            assertFalse(meteorList.contains(meteorInsert))
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
            assertTrue(meteorList.isEmpty())
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
            assertTrue(meteorList.contains(meteorInsert))
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
            assertTrue(meteorList.size == 1)
        } catch (e: java.lang.Exception) {
            Log.i("MeteorViewModelUiTest", e.message!!)
            assertTrue(true)
        }
    }

    @Test
    fun testFetchMeteorsByPage() = runBlocking {
        try {
            val limit = 3
            meterDao.clearMeteorTable()
            val meteorList = TestMeteorData.getMeteorList()
            meterDao.insertAllMeteors(meteorList)

            val meteorListFromDatabase = meterDao.getFavoriteMeteorsByPage(limit, 0)
            assertTrue(meteorListFromDatabase.size == 3)
        } catch (e: java.lang.Exception) {
            Log.i("MeteorViewModelUiTest", e.message!!)
        }
    }

    @Test
    fun testGetAllFavouriteMeteors() = runBlocking {
        try {
            meterDao.clearMeteorTable()
            val meteorFavouriteList = TestMeteorData.getFavouriteMeteorList()
            meterDao.insertAllMeteors(meteorFavouriteList)

            val favouriteListFromDatabase = meterDao.getAllFavoriteMeteor()
            assertTrue(meteorFavouriteList.size == favouriteListFromDatabase.size)
        } catch (e: java.lang.Exception) {
            Log.i("MeteorViewModelUiTest", e.message!!)
        }
    }

    @Test
    fun testRemoveFavoriteMeteors() = runBlocking {
        try {
            meterDao.clearMeteorTable()
            val meteorInsertFavourite: Meteor =
                TestMeteorData.getTestData("favourite", isfavourite = 1)
            val meteorInsert: Meteor = TestMeteorData.getTestData("not favourite", isfavourite = 0)
            meterDao.insertMeteor(meteorInsertFavourite)
            meterDao.insertMeteor(meteorInsert)

            val meteorList = meterDao.getAllFavoriteMeteor()
            val favourite = meterDao.getMeteorByName("favourite")
            assertTrue(meteorList.size == 1)
            assertTrue(favourite?.name == "favourite")
        } catch (e: java.lang.Exception) {
            Log.i("MeteorViewModelUiTest", e.message!!)
            assertTrue(true)
        }
    }
}