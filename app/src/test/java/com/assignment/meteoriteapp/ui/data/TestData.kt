package com.assignment.meteoriteapp.ui.data

import com.assignment.meteoriteapp.testData.Meteor


/**
 * Created by Sibaprasad Mohanty on 17/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class TestData {
    companion object {

        fun getMeteorList() : ArrayList<Meteor>{
            val list = ArrayList<Meteor>()
            list.add(getTestData("efgh", "101", "2001-01-03", 0))
            list.add(getTestData("abcd", "100", "2000-01-03", 1))
            list.add(getTestData("mnop", "103", "2003-01-03", 1))
            list.add(getTestData("ijkl", "102", "2002-01-03", 1))
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