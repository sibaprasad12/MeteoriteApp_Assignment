package com.assignment.meteoriteapp.data

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


/**
 * Created by Sibaprasad Mohanty on 14/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

@Keep
data class MeteorResponse(val list: List<Meteor>)

@Keep
@Entity
@Parcelize
data class Meteor(
    val fall: String, // Fell
    val id: String, // 24019
    val mass: String, // 600
    @PrimaryKey val name: String, // Tomakovka
    val nametype: String, // Valid
    val recclass: String, // LL6
    val reclat: String, // 47.850000
    val reclong: String, // 34.766670
    val year: String, // 1905-01-01T00:00:00.000
    var isFavorite: Int = 0, // 1905-01-01T00:00:00.000
    var position: Int = 0 // 1905-01-01T00:00:00.000
) : Parcelable {
    fun getDateTime() = year.substring(0, 10)
    fun getOnlyYear() = year.substring(0, 4)
}


@Keep
data class Geolocation(
    val coordinates: List<Double>,
    val type: String // Point
)