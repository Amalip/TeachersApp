package com.amalip.teachers.domain.model

import com.squareup.moshi.JsonClass

/**
 * Created by Amalip on 12/5/2021.
 */

@JsonClass(generateAdapter = true)
data class User(

    val id: Int = 0,
    val name: String = "",
    val firstLastname: String = "",
    val secondLastname: String = "",
    val email: String = "",
    val picture: String = "",
    val enrollment: String = "",
    val password: String = "",
    val level: Int = 0

) {

    val fullName: String
        get() = "$name $firstLastname $secondLastname"

}