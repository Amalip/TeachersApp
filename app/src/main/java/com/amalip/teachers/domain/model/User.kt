package com.amalip.teachers.domain.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 * Created by Amalip on 12/5/2021.
 */

@Parcelize
@JsonClass(generateAdapter = true)
data class User(

    val id: Int = 0,
    var name: String = "",
    var firstLastname: String = "",
    var secondLastname: String = "",
    var email: String = "",
    var picture: String = "",
    val enrollment: String = "",
    val password: String = "",
    val level: Int = 0

) : Parcelable {

    val fullName: String
        get() = "$name $firstLastname $secondLastname"

}