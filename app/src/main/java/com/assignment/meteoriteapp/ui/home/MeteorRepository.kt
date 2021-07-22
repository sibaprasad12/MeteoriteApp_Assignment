package com.assignment.meteoriteapp.ui.home

import com.assignment.meteoriteapp.data.Meteor
import com.assignment.meteoriteapp.database.MeteorDao
import com.assignment.meteoriteapp.network.ApiHelperImpl
import javax.inject.Inject


/**
 * Created by Sibaprasad Mohanty on 14/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class MeteorRepository @Inject constructor(
    private val apiHelper: ApiHelperImpl,
    private val meteorDao: MeteorDao
) {
    suspend fun getMeteors() = apiHelper.getUsers()
    suspend fun getAllSavedMeteor() = meteorDao.getAllSavedMeteor()
    suspend fun insertMeteor(meteor: Meteor) = meteorDao.insertMeteor(meteor)
    suspend fun insertAllMeteors(meteorList: List<Meteor>) = meteorDao.insertAllMeteors(meteorList)

    suspend fun getRowCount() = meteorDao.getRowCount()
    suspend fun getFavoriteMeteorsByPage(limit: Int, offset: Int) =
        meteorDao.getFavoriteMeteorsByPage(limit, offset)

    suspend fun getMeteorByName(meteorName: String) = meteorDao.getMeteorByName(meteorName)
    suspend fun clearMeteorTable() = meteorDao.clearMeteorTable()
    suspend fun deleteMeteor(meteor: Meteor) = meteorDao.deleteMeteor(meteor)
    suspend fun getAllFavoriteMeteor() = meteorDao.getAllFavoriteMeteor()
}