package com.assignment.meteoriteapp.extra

import android.content.Context
import androidx.lifecycle.Observer
import com.assignment.meteoriteapp.testData.Meteor
import com.assignment.meteoriteapp.database.AppDatabase
import com.assignment.meteoriteapp.network.ApiHelper
import com.assignment.meteoriteapp.network.ResponseState
import com.assignment.meteoriteapp.ui.home.MeteorRepository
import com.assignment.meteoriteapp.ui.home.MeteorViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SingleNetworkCallViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiHelper: ApiHelper

    @Mock
    private lateinit var applicationContext: Context

    @Mock
    private lateinit var repository: MeteorRepository

    @Mock
    private lateinit var databaseHelper: AppDatabase

    @Mock
    private lateinit var apiUsersObserver: Observer<ResponseState<List<Meteor>>>

    @Before
    fun setUp() {
        // do something if required
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<Meteor>())
                .`when`(apiHelper)
                .getUsers()
            val viewModel = MeteorViewModel(applicationContext, repository)
            viewModel.getMeteors().observeForever(apiUsersObserver)
            verify(apiHelper).getUsers()
            verify(apiUsersObserver).onChanged(ResponseState.success(emptyList()))
            viewModel.getMeteors().removeObserver(apiUsersObserver)
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message For You"
            doThrow(RuntimeException(errorMessage))
                .`when`(apiHelper)
                .getUsers()
            val viewModel = MeteorViewModel(applicationContext, repository)
            viewModel.getMeteors().observeForever(apiUsersObserver)
            verify(apiHelper).getUsers()
            verify(apiUsersObserver).onChanged(
                ResponseState.error(
                    null,
                    RuntimeException(errorMessage).toString()
                )
            )
            viewModel.getMeteors().removeObserver(apiUsersObserver)
        }
    }

    @After
    fun tearDown() {
        // do something if required
    }

}