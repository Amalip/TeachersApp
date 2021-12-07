package com.amalip.teachers.data.api

import com.amalip.teachers.data.dto.SetGradeRequest
import com.amalip.teachers.domain.model.Course
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

/**
 * Created by Amalip on 12/6/2021.
 */
interface CourseApi {

    @GET("course/byUser")
    fun getCoursesByUser(@Query("userId") userId: Int): Call<List<Course>>

    @PUT("course")
    fun setGrade(@Body setGradeRequest: SetGradeRequest): Call<Unit>

}