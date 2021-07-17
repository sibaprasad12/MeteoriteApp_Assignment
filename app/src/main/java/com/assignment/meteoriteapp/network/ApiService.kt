package com.assignment.meteoriteapp.network

import com.assignment.meteoriteapp.data.Meteor
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

/**
 * Created by Sibaprasad Mohanty on 14/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

interface ApiService {
    @GET("y77d-th95.json")
    suspend fun getMeteors(): List<Meteor>
}