package com.assignment.meteoriteapp.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.assignment.meteoriteapp.testData.TestMeteorData
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


@ExperimentalCoroutinesApi
@HiltAndroidTest
@SmallTest
class MeteorDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabase
    private lateinit var meteorDao: MeteorDao

    @Before
    fun setup() {
        hiltRule.inject()
        meteorDao = database.meteorDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertUser() = runBlocking {
        val meteor = TestMeteorData.getTestData("abcd")
        meteorDao.insertMeteor(meteor)
        val allUsers = meteorDao.getAllSavedMeteor()
        assertThat(allUsers).contains(meteor)

    }
}