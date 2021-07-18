package com.assignment.meteoriteapp.database

import androidx.room.*
import com.assignment.meteoriteapp.testData.Meteor


/**
 * Created by Sibaprasad Mohanty on 14/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

@Dao
interface MeteorDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertMeteor(meteors: Meteor)

    @Delete
    suspend fun deleteMeteor(meteor: Meteor)

    @Update
    suspend fun updateMeteor(meteor: Meteor)

    @Query("SELECT * from meteor")
    suspend fun getAllSavedMeteor(): List<Meteor>


    @Query("SELECT * FROM meteor WHERE isFavorite > 0")
    suspend fun getAllFavoriteMeteor(): List<Meteor>

    @Query("SELECT * FROM meteor LIMIT :limit OFFSET :offset")
    suspend fun loadSavedMeteorByPage(limit: Int, offset: Int): List<Meteor>

    @Query("SELECT COUNT(name) FROM meteor")
    suspend fun getRowCount(): Int

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAllMeteors(meteorList : List<Meteor>)

    @Query("SELECT * FROM meteor WHERE isFavorite > 0 LIMIT :limit OFFSET :offset")
    suspend fun getFavoriteMeteorsByPage(limit: Int, offset: Int): List<Meteor>

    @Query("SELECT * FROM meteor WHERE name =:name")
    suspend fun getMeteorByName(name: String): Meteor?

    @Query("DELETE FROM meteor")
    suspend fun clearMeteorTable()
}