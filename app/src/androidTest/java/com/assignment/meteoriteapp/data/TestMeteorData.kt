package com.assignment.meteoriteapp.data


/**
 * Created by Sibaprasad Mohanty on 17/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class TestMeteorData {
    companion object {

        fun getMeteorList(): ArrayList<Meteor> {
            val list = ArrayList<Meteor>()
            list.add(TestMeteorData.getTestData("efgh", "101", "2001-01-03", 0))
            list.add(TestMeteorData.getTestData("abcd", "100", "2000-01-03", 1))
            list.add(TestMeteorData.getTestData("mnop", "103", "2003-01-03", 1))
            list.add(TestMeteorData.getTestData("ijkl", "102", "2002-01-03", 1))
            return list
        }

        fun getFavouriteMeteorList(): ArrayList<Meteor> {
            val list = ArrayList<Meteor>()
            list.add(TestMeteorData.getTestData("efgh", "101", "2001-01-03", 1))
            list.add(TestMeteorData.getTestData("abcd", "100", "2000-01-03", 1))
            list.add(TestMeteorData.getTestData("mnop", "103", "2003-01-03", 1))
            list.add(TestMeteorData.getTestData("ijkl", "102", "2002-01-03", 1))
            return list
        }

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