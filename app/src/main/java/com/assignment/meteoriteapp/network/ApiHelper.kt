package com.assignment.meteoriteapp.network

import com.assignment.meteoriteapp.testData.Meteor

/**
 * Created by Sibaprasad Mohanty on 14/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

interface ApiHelper {
    suspend fun getUsers(): List<Meteor>
}