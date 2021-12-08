package com.amalip.teachers.domain.repository

import com.amalip.teachers.core.exception.Failure
import com.amalip.teachers.core.functional.Either
import com.amalip.teachers.data.dto.SetGradeRequest
import com.amalip.teachers.domain.model.Course

/**
 * Created by Amalip on 12/6/2021.
 */

interface CourseRepository {

    fun getCoursesByUser(userId: Int): Either<Failure, List<Course>>

    fun setGrade(setGradeRequest: SetGradeRequest): Either<Failure, Boolean>

    fun getCoursesByIdAndUser(courseId: Int, userId: Int): Either<Failure, Course>

}