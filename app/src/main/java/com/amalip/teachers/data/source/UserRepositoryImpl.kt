package com.amalip.teachers.data.source

import com.amalip.teachers.core.enums.UserLevel
import com.amalip.teachers.core.exception.Failure
import com.amalip.teachers.core.functional.Either
import com.amalip.teachers.core.functional.getOrElse
import com.amalip.teachers.core.plataform.AuthManager
import com.amalip.teachers.core.plataform.NetworkHandler
import com.amalip.teachers.data.api.UserApi
import com.amalip.teachers.domain.model.User
import com.amalip.teachers.domain.repository.UserRepository
import com.amalip.teachers.framework.api.ApiRequest
import com.amalip.teachers.presentation.course.detail.CourseFailure
import com.amalip.teachers.presentation.login.LoginFailure
import com.amalip.teachers.presentation.profile.ProfileFailure
import javax.inject.Inject

/**
 * Created by Amalip on 12/5/2021.
 */

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    private val authManager: AuthManager,
    private val networkHandler: NetworkHandler
) : UserRepository, ApiRequest {

    override fun getLocalUser(): Either<Failure, User> {
        val result = authManager.user

        return result?.let {
            Either.Right(it)
        } ?: Either.Left(Failure.Unauthorized)
    }

    override fun doLogout(): Either<Failure, Boolean> {
        authManager.user = null
        return Either.Right(true)
    }

    override fun findUser(enrollment: String, password: String): Either<Failure, User> {
        val result = makeRequest(
            networkHandler,
            userApi.login(
                User(
                    enrollment = enrollment,
                    password = password,
                    level = UserLevel.TEACHER.code
                )
            ),
            { it },
            User()
        )

        return if (result.isRight) {
            authManager.user = result.getOrElse(User())

            result
        } else Either.Left(LoginFailure.NotFound)
    }

    override fun getStudentsByCourse(courseId: Int): Either<Failure, List<User>> {
        val result = makeRequest(
            networkHandler, userApi.getStudentsByCourse(courseId), { it }, emptyList()
        )

        return if (result.isRight) result
        else Either.Left(CourseFailure.NotFound)
    }

    override fun updateUser(user: User): Either<Failure, Boolean> {
        val result = makeRequest(networkHandler, userApi.updateUser(user), {  }, Unit)

        return if (result.isRight) {
            authManager.user = user

            Either.Right(true)
        }
        else Either.Left(ProfileFailure.CouldNotUpdate)
    }

}