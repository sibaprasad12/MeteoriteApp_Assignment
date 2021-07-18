package com.assignment.meteoriteapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment.meteoriteapp.testData.Meteor

/**
 * Created by Sibaprasad Mohanty on 14/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

@Database(entities = [Meteor::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun meteorDao(): MeteorDao
}