package com.assignment.meteoriteapp.network


/**
 * Created by Sibaprasad Mohanty on 17/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

import com.assignment.meteoriteapp.data.Meteor
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getUsers(): List<Meteor> = apiService.getMeteors()

}