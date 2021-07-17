package com.assignment.meteoriteapp.ui.data

import com.assignment.meteoriteapp.data.Meteor


/**
 * Created by Sibaprasad Mohanty on 17/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class TestData {
    companion object {

        fun getMeteorList() : ArrayList<Meteor>{
            val list = ArrayList<Meteor>()
            list.add(getTestData("name1"))
            list.add(getTestData("name3"))
            list.add(getTestData("name2"))
            return list
        }

        fun getTestData(
            name: String,
            mass: String = "123 KG",
            year: String = "2020-01-12",
            isfavourite : Int = 0
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
                1,
                0
            )
        }
    }
}