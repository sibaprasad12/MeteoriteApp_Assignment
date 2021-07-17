package com.assignment.meteoriteapp.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


/**
 * Created by Sibaprasad Mohanty on 14/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

@HiltAndroidApp
class MeteorApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}