package com.assignment.meteoriteapp.data


/**
 * Created by Sibaprasad Mohanty on 17/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class TestMeteorData {
    companion object {
        fun getTestData(
            name: String,
            mass: String = "123 KG",
            year: String = "2020-01-12",
            isfavourite: Int = 1
        ): Meteor {
            return Meteor(
                "Fell",
                "1",
                mass,
                name,
                "nametype",
                "recclass",
                "reclat",
                "reclong",
                year,
                isfavourite,
                0
            )
        }
    }
}