package com.assignment.meteoriteapp.controllers

import com.assignment.meteoriteapp.testData.Meteor


/**
 * Created by Sibaprasad Mohanty on 15/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

interface OnMeteorClickListener {
    fun onMeteorMapClick(meteor: Meteor)
    fun onFevoriteClick(meteor: Meteor)
}