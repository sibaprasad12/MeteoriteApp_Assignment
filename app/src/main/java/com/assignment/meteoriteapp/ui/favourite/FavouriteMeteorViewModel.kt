package com.assignment.meteoriteapp.ui.favourite

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.assignment.meteoriteapp.data.Meteor
import com.assignment.meteoriteapp.network.ResponseState
import com.assignment.meteoriteapp.ui.base.BaseViewModel
import com.assignment.meteoriteapp.ui.home.MeteorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Sibaprasad Mohanty on 14/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

@HiltViewModel
class FavouriteMeteorViewModel @Inject constructor(
    @ApplicationContext
    private val application: Context, private val repository: MeteorRepository
) : BaseViewModel() {

    val favouriteMeteorList = MutableLiveData<ResponseState<List<Meteor>>>()
    lateinit var viewModel: FavouriteMeteorViewModel

    private var limit = 10

    fun fetchMeteorsByPage() {
        viewModelScope.launch {
            obsevableLoading.set(true)
            try {
                val numberOfRows = repository.getRowCount()
                limit = if (numberOfRows < limit) numberOfRows else limit
                val countriesFromDb = repository.getFavoriteMeteorsByPage(limit, 0)

                if (isValidMeteorList(countriesFromDb)) {
                    favouriteMeteorList.postValue(ResponseState.success(countriesFromDb))
                    errorMessage.set("")
                } else {
                    errorMessage.set("No Data found")
                }
                obsevableLoading.set(false)
            } catch (e: Exception) {
                obsevableLoading.set(false)
                errorMessage.set(e.message ?: "Error Occurred!")
            }
        }
    }

    fun getAllFavouriteMeteors() {
        viewModelScope.launch {
            obsevableLoading.set(true)
            try {
                val countriesFromDb = repository.getAllFavoriteMeteor()
                if (isValidMeteorList(countriesFromDb)) {
                    favouriteMeteorList.postValue(ResponseState.success(countriesFromDb))
                } else {
                    favouriteMeteorList.postValue(ResponseState.error(emptyList(), "No Data found"))
                }
                obsevableLoading.set(false)
            } catch (e: Exception) {
                obsevableLoading.set(false)
                errorMessage.set(e.message ?: "Error Occurred!")
            }
        }
    }

    fun removeFavoriteMeteors(meteor: Meteor) {
        if (isValidMeteor(meteor)) {
            CoroutineScope(Dispatchers.IO).launch {
                repository.deleteMeteor(meteor)
                delay(1000)
                fetchMeteorsByPage()
            }
        }
    }
}