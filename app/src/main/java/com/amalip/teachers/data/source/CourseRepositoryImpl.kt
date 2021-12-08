package com.amalip.teachers.data.source

import com.amalip.teachers.core.exception.Failure
import com.amalip.teachers.core.functional.Either
import com.amalip.teachers.core.plataform.NetworkHandler
import com.amalip.teachers.data.api.CourseApi
import com.amalip.teachers.data.dto.SetGradeRequest
import com.amalip.teachers.domain.model.Course
import com.amalip.teachers.domain.repository.CourseRepository
import com.amalip.teachers.framework.api.ApiRequest
import com.amalip.teachers.presentation.course.list.CoursesFailure
import javax.inject.Inject

/**
 * Created by Amalip on 12/5/2021.
 */

class CourseRepositoryImpl @Inject constructor(
    private val courseApi: CourseApi,
    private val networkHandler: NetworkHandler
) : CourseRepository, ApiRequest {

    override fun getCoursesByUser(userId: Int): Either<Failure, List<Course>> {
        val result = makeRequest(
            networkHandler, courseApi.getCoursesByUser(userId), { it }, emptyList()
        )

        return if (result.isRight) result
        else Either.Left(CoursesFailure.NotFound)

    }

    override fun setGrade(setGradeRequest: SetGradeRequest): Either<Failure, Boolean> {
        val result = makeRequest(
            networkHandler, courseApi.setGrade(setGradeRequest), { }, Unit
        )

        return if (result.isRight) Either.Right(true)
        else Either.Left(CoursesFailure.CouldNotUpdate)
    }

    override fun getCoursesByIdAndUser(courseId: Int, userId: Int): Either<Failure, Course> {
        val result = makeRequest(
            networkHandler, courseApi.getCoursesByIdAndUser(courseId, userId), { it }, Course()
        )

        return if (result.isRight) result
        else Either.Left(CoursesFailure.NotFound)
    }

}