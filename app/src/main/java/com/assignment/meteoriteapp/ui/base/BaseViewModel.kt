package com.assignment.meteoriteapp.ui.base

import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.assignment.meteoriteapp.testData.Meteor


/**
 * Created by Sibaprasad Mohanty on 15/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

open class BaseViewModel : ViewModel(), Observable {

    var obsevableLoading = ObservableBoolean(false)
    var loadMore = ObservableBoolean(false)

    var errorMessage = ObservableField("")

    fun isValidMeteor(meteor: Meteor?): Boolean {
        return (meteor != null
                && !meteor.mass.isNullOrEmpty())
    }

    fun isValidMeteorList(listMeteor: List<Meteor>) =
        listMeteor.isNotEmpty()

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}