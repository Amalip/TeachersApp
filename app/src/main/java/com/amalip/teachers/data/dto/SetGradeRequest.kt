package com.amalip.teachers.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SetGradeRequest(

    val userId: Int = 0,
    val courseId: Int = 0,
    val grade: Double = 0.0,
    val partial: Int = 0

)
