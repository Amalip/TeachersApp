package com.amalip.teachers.data.api

import com.amalip.teachers.core.enums.UserLevel
import com.amalip.teachers.domain.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by Amalip on 12/5/2021.
 */

interface UserApi {

    @POST("user/login")
    fun login(@Body user: User): Call<User>

    @GET("user/byCourse")
    fun getStudentsByCourse(
        @Query("courseId") courseId: Int,
        @Query("userType") userLevel: Int = UserLevel.STUDENT.code
    ): Call<List<User>>

}