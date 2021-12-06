package com.amalip.teachers.data.api

import com.amalip.teachers.domain.model.User
import retrofit2.Call
import retrofit2.http.POST

/**
 * Created by Amalip on 12/5/2021.
 */

interface UserApi {

    @POST("user/login")
    fun login(user: User): Call<User>

}