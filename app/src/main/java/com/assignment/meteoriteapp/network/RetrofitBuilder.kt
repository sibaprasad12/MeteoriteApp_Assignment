package com.assignment.meteoriteapp.network

import com.assignment.meteoriteapp.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Sibaprasad Mohanty on 14/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

object RetrofitBuilder {

    private const val BASE_URL = "https://data.nasa.gov/resource/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}