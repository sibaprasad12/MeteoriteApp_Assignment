package com.assignment.meteoriteapp.di

import android.content.Context
import androidx.room.Room
import com.assignment.meteoriteapp.database.AppDatabase
import com.assignment.meteoriteapp.database.MeteorDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app.db"
        ).build()
    }

    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): MeteorDao {
        return appDatabase.meteorDao()
    }
}