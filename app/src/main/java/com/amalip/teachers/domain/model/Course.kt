package com.amalip.teachers.domain.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Course(

    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val picture: String = "",
    var scheduleList: MutableList<String> = mutableListOf(),
    var grades: MutableList<Double> = mutableListOf()

) : Parcelable
