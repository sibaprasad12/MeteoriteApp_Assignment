package com.assignment.meteoriteapp.ui.home

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.assignment.meteoriteapp.data.Meteor
import com.assignment.meteoriteapp.network.ResponseState
import com.assignment.meteoriteapp.ui.base.BaseViewModel
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
class MeteorViewModel  @Inject constructor(
    @ApplicationContext private val application: Context?,
    private val meteorRepository: MeteorRepository
) :
    BaseViewModel() {

    private var lastPosition = 0
    private var filterType = DEFAULT
    private var meteorsList: List<Meteor> = listOf()
    var meteorsListByPage = ArrayList<Meteor>()
    private var meteorListLoadMore: List<Meteor> = listOf()

    companion object {
        val DEFAULT = -1
        val FILTER_BY_WEIGHT = 0
        val FILTER_BY_WEIGHT_DESC = 1
        val FILTER_BY_YEAR = 2
        val FILTER_BY_YEAR_DESC = 3
        val FILTER_BY_FALL = 4
        val FILTER_BY_NAME = 5
    }

    fun getMeteors() = liveData(Dispatchers.IO) {
        if (meteorsList.isEmpty()) {
            obsevableLoading.set(true)
            try {
                val listMeteors = meteorRepository.getMeteors()

                meteorsList = listMeteors.filter {
                    val year = it.year
                    try {
                        year.substring(0, 4).toInt() > 1900 && it.mass != null
                    } catch (e: Exception) {
                        false
                    }
                }
                meteorListLoadMore = meteorsList
                emit(ResponseState.success(meteorsList))
                insertAllMeteors(meteorListLoadMore)
                obsevableLoading.set(false)
            } catch (exception: Exception) {
                obsevableLoading.set(false)
                errorMessage.set(exception.message ?: "Error Occurred!")
                emit(ResponseState.error(null, exception.message ?: "Error Occurred!"))
            }
        } else {
            obsevableLoading.set(false)
            emit(ResponseState.success(meteorsList))
        }
    }

    fun getAllFevoriteMeteors() {
        viewModelScope.launch {
            obsevableLoading.set(true)
            try {
                val countriesFromDb = meteorRepository.getAllSavedMeteor()
                if (isValidMeteorList(countriesFromDb)) {

                }
                obsevableLoading.set(false)
            } catch (e: Exception) {
                obsevableLoading.set(false)
                errorMessage.set(e.message ?: "Error Occurred!")
            }
        }
    }

    fun filterMeteors(filter: Int) = when (filter) {
        FILTER_BY_WEIGHT -> {
            filterType = filter
            meteorsListByPage.sortedBy {
                it.mass.toDouble()
            }
        }
        FILTER_BY_WEIGHT_DESC -> {
            filterType = filter
            meteorsListByPage.sortedByDescending {
                it.mass.toDouble()
            }
        }
        FILTER_BY_YEAR -> {
            filterType = filter
            meteorsListByPage.sortedBy {
                it.getOnlyYear().toInt()
            }
        }
        FILTER_BY_YEAR_DESC -> {
            filterType = filter
            meteorsListByPage.sortedByDescending {
                it.getOnlyYear().toInt()
            }
        }
        FILTER_BY_FALL -> {
            filterType = filter
            meteorsListByPage.sortedBy {
                it.fall
            }
        }
        FILTER_BY_NAME -> {
            filterType = filter
            meteorsListByPage.sortedBy {
                it.name
            }
        }
        else -> {
            filterType = DEFAULT
            meteorsListByPage
        }
    }

    fun fetchMeteorsByPage() = liveData(Dispatchers.IO) {
        loadMore.set(true)
        try {
            val sublist: ArrayList<Meteor> =
                ArrayList<Meteor>(meteorListLoadMore.subList(lastPosition, lastPosition + 20))
            meteorsListByPage.addAll(sublist)
            lastPosition += 20
            delay(2000)
            emit(ResponseState.success(ArrayList(filterMeteors(filterType))))
            loadMore.set(false)
        } catch (exception: Exception) {
            loadMore.set(false)
            errorMessage.set(exception.message ?: "Error Occurred!")
            emit(ResponseState.error(null, exception.message ?: "Error Occurred!"))
        }
    }

    fun insertFavoriteMeteors(meteor: Meteor) {
        if (isValidMeteor(meteor)) {
            meteor.isFavorite = 1
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    meteorRepository.insertMeteor(meteor)
                    delay(1000)
                } catch (e: java.lang.Exception) {
                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(
                            application,
                            "Error : Meteor ${meteor.name} Already Exist",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }
            }
        }
    }

    private fun insertAllMeteors(meteors: List<Meteor>) {
        if (isValidMeteorList(meteors)) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    meteorRepository.insertAllMeteors(meteors)
                    delay(1000)
                } catch (e: java.lang.Exception) {

                }
            }
        }
    }

    fun isValidMeteorList() = super.isValidMeteorList(meteorListLoadMore)
}
