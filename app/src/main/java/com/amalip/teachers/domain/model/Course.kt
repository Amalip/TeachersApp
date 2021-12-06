package com.amalip.teachers.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Course(

    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val picture: String = "",
    var scheduleList: MutableList<String> = mutableListOf(),
    var grades: MutableList<Int> = mutableListOf()

)
