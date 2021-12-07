package com.amalip.teachers.domain.repository

import com.amalip.teachers.core.exception.Failure
import com.amalip.teachers.core.functional.Either
import com.amalip.teachers.domain.model.User

/**
 * Created by Amalip on 12/5/2021.
 */

interface UserRepository {

    fun getLocalUser(): Either<Failure, User>

    fun doLogout(): Either<Failure, Boolean>

    fun findUser(enrollment: String, password: String): Either<Failure, User>

    fun getStudentsByCourse(courseId: Int): Either<Failure, List<User>>

}