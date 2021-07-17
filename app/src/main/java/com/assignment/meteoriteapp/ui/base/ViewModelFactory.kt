package com.assignment.meteoriteapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assignment.meteoriteapp.network.ApiHelper
import com.assignment.meteoriteapp.ui.favourite.FavouriteMeteorViewModel
import com.assignment.meteoriteapp.ui.home.MeteorViewModel
import com.assignment.meteoriteapp.ui.home.MeteorRepository

class ViewModelFactory(private val apiHelper: ApiHelper? = null) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       /* if (modelClass.isAssignableFrom(MeteorViewModel::class.java)) {
            return MeteorViewModel(MeteorRepository(apiHelper!!)) as T
        } else if (modelClass.isAssignableFrom(FavouriteMeteorViewModel::class.java)) {
            return FavouriteMeteorViewModel() as T
        }*/
        throw IllegalArgumentException("Unknown class name")
    }

}