package com.amalip.teachers.domain.usecase

import com.amalip.teachers.core.interactor.UseCase
import com.amalip.teachers.domain.model.Course
import com.amalip.teachers.domain.repository.CourseRepository
import javax.inject.Inject

/**
 * Created by Amalip on 12/6/2021.
 */
class GetCourseByIdAndUserId @Inject constructor(private val courseRepository: CourseRepository) :
    UseCase<Course, Pair<Int, Int>>() {

    /** Params:
     * First -> Course ID
     * Second -> User ID
     */

    override suspend fun run(params: Pair<Int, Int>) =
        courseRepository.getCoursesByIdAndUser(params.first, params.second)

}